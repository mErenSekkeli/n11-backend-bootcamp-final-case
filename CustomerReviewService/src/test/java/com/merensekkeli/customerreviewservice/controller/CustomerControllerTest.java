package com.merensekkeli.customerreviewservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.merensekkeli.customerreviewservice.CustomerReviewServiceApplication;
import com.merensekkeli.customerreviewservice.controller.contract.CustomerControllerContract;
import com.merensekkeli.customerreviewservice.dto.CustomerDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.enums.EnumStatus;
import com.merensekkeli.customerreviewservice.exception.ItemNotFoundException;
import com.merensekkeli.customerreviewservice.request.CustomerSaveRequest;
import com.merensekkeli.customerreviewservice.request.CustomerUpdateRequest;
import com.merensekkeli.customerreviewservice.service.entityservice.CustomerEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = CustomerReviewServiceApplication.class)
public class CustomerControllerTest extends BaseControllerTest{

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private CustomerControllerContract customerControllerContract;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldSaveCustomer() throws Exception {
        CustomerSaveRequest request = new CustomerSaveRequest();
        request.setName("John");
        request.setSurname("Doe");
        request.setBirthDate(LocalDate.of(1990, 1, 1));
        request.setUsername("johndoe");
        request.setPhoneNumber("+1234567890");
        request.setEmail("mail@mail.com");
        request.setStatus(EnumStatus.ACTIVE);
        request.setLatitude(40.712776);
        request.setLongitude(-74.005974);

        CustomerDTO mockCustomerDTO = new CustomerDTO(
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "johndoe",
                "+1234567890",
                "mail@mail.com",
                EnumStatus.ACTIVE,
                40.712776,
                -74.005974
        );

        String requestAsString = objectMapper.writeValueAsString(request);

        Mockito.when(customerControllerContract.saveCustomer(Mockito.any(CustomerSaveRequest.class))).thenReturn(mockCustomerDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);

        Mockito.verify(customerControllerContract, Mockito.times(1)).saveCustomer(Mockito.any(CustomerSaveRequest.class));
    }

    @Test
    void shouldNotSaveCustomerWhenNameOrUsernameIsBlank() throws Exception {

        String requestAsString = "{\n"
                + "  \"name\": \"\",\n"
                + "  \"surname\": \"string\",\n"
                + "  \"birthDate\": \"2024-02-24\",\n"
                + "  \"username\": \"\",\n"
                + "  \"phoneNumber\": \"+90555555555555\",\n"
                + "  \"email\": \"mail@mail.com\",\n"
                + "  \"status\": \"ACTIVE\",\n"
                + "  \"latitude\": \"0.0\", \n"
                + "  \"longitude\": \"0.0\"\n"
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldGetCustomerByIdSuccessfully() throws Exception {
        long id = 1L;
        CustomerDTO mockCustomerDTO = new CustomerDTO(
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "johndoe",
                "+1234567890",
                "john.doe@mail.com",
                EnumStatus.ACTIVE,
                40.712776,
                -74.005974
        );

        // Assume the controller or service layer has a method called getCustomerById
        Mockito.when(customerControllerContract.getCustomerById(id)).thenReturn(mockCustomerDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Optionally verify the service method was called
        Mockito.verify(customerControllerContract).getCustomerById(id);
    }

    @Test
    void shouldHandleNonExistentCustomer() throws Exception {
        long id = 999999L;

        Mockito.when(customerControllerContract.getCustomerById(id))
                .thenThrow(new ItemNotFoundException("Customer not found with id: " + id));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldGetAllCustomersSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        long id = 1L;
        CustomerUpdateRequest request = new CustomerUpdateRequest();
        request.setName("John");
        request.setSurname("Doe");
        request.setBirthDate(LocalDate.of(1990, 1, 1));
        request.setLatitude(40.712775);
        request.setLongitude(-74.005974);
        request.setStatus(EnumStatus.ACTIVE);

        CustomerDTO mockCustomerDTO = new CustomerDTO(
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "johndoe",
                "+1234567890",
                "mail.mail.com",
                EnumStatus.ACTIVE,
                40.712775,
                -74.005974
        );

        String requestAsString = objectMapper.writeValueAsString(request);

        Mockito.when(customerControllerContract.updateCustomer(Mockito.anyLong(), Mockito.any(CustomerUpdateRequest.class))).thenReturn(mockCustomerDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer/" + id)
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);

        Mockito.verify(customerControllerContract, Mockito.times(1)).updateCustomer(Mockito.anyLong(), Mockito.any(CustomerUpdateRequest.class));
    }

    @Test
    void shouldDeleteCustomerSuccessfully() throws Exception {
        long id = 1L;

        Mockito.doNothing().when(customerControllerContract).deleteCustomer(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customer/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(customerControllerContract, Mockito.times(1)).deleteCustomer(id);
    }

    @Test
    void shouldHandleNonExistentCustomerDelete() throws Exception {
        long id = 999999L;

        Mockito.doThrow(new ItemNotFoundException("Customer not found with id: " + id))
                .when(customerControllerContract).deleteCustomer(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customer/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
