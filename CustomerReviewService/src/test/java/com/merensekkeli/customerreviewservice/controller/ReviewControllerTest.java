package com.merensekkeli.customerreviewservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.merensekkeli.customerreviewservice.CustomerReviewServiceApplication;
import com.merensekkeli.customerreviewservice.client.CompanyClient;
import com.merensekkeli.customerreviewservice.controller.contract.ReviewControllerContract;
import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;
import com.merensekkeli.customerreviewservice.dto.ReviewDTO;
import com.merensekkeli.customerreviewservice.entity.Customer;
import com.merensekkeli.customerreviewservice.entity.Review;
import com.merensekkeli.customerreviewservice.enums.EnumRate;
import com.merensekkeli.customerreviewservice.enums.EnumStatus;
import com.merensekkeli.customerreviewservice.exception.ItemNotFoundException;
import com.merensekkeli.customerreviewservice.faker.CustomerFaker;
import com.merensekkeli.customerreviewservice.request.ReviewSaveRequest;
import com.merensekkeli.customerreviewservice.request.ReviewUpdateRequest;
import com.merensekkeli.customerreviewservice.service.entityservice.CustomerEntityService;
import com.merensekkeli.customerreviewservice.service.entityservice.ReviewEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = CustomerReviewServiceApplication.class)
public class ReviewControllerTest extends BaseControllerTest{

    private MockMvc mockMvc;

    @MockBean
    private ReviewControllerContract reviewControllerContract;

    @MockBean
    private ReviewEntityService reviewEntityService;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CompanyClient companyClient;

    @MockBean
    private CustomerEntityService customerEntityService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldSaveReview() throws Exception {
        ReviewSaveRequest reviewSaveRequest = new ReviewSaveRequest();
        reviewSaveRequest.setCustomerId(1L);
        reviewSaveRequest.setCompanyId("8511b204-06b2-4710-9d40-f8e2b61c701d");
        reviewSaveRequest.setRate(EnumRate.FIVE);
        reviewSaveRequest.setComment("Great service!");

        ReviewDTO mockReviewDTO = new ReviewDTO("johndoe", "8511b204-06b2-4710-9d40-f8e2b61c701d", EnumRate.FIVE, "Great service!");
        CompanyClientDTO mockCompanyClientDTO = new CompanyClientDTO(
                "8511b204-06b2-4710-9d40-f8e2b61c701d",
                "Company Name",
                "Company Address",
                "Company Phone",
                "company@example.com",
                "https://company.com",
                40.712776,
                -74.005974,
                EnumStatus.ACTIVE,
                null
        );
        Customer mockCustomer = CustomerFaker.customer();

        String requestJson = new ObjectMapper().writeValueAsString(reviewSaveRequest);

        Mockito.when(reviewEntityService.existsByCustomerIdAndProductId(reviewSaveRequest.getCustomerId(), reviewSaveRequest.getCompanyId())).thenReturn(false);
        Mockito.when(companyClient.getCompany(reviewSaveRequest.getCompanyId())).thenReturn(mockCompanyClientDTO);
        Mockito.when(reviewEntityService.save(any(Review.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.when(customerEntityService.findByIdWithControl(reviewSaveRequest.getCustomerId())).thenReturn(mockCustomer);
        Mockito.when(reviewControllerContract.saveReview(any(ReviewSaveRequest.class))).thenReturn(mockReviewDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);

        Mockito.verify(reviewControllerContract, Mockito.times(1)).saveReview(any(ReviewSaveRequest.class));
    }

    @Test
    void shouldReturnReviewWhenExists() throws Exception {
        ReviewDTO mockReviewDTO = new ReviewDTO("johndoe", "8511b204-06b2-4710-9d40-f8e2b61c701d", EnumRate.FIVE, "Great service!");
        when(reviewControllerContract.getReview(anyLong())).thenReturn(mockReviewDTO);

        mockMvc.perform(get("/api/v1/reviews/1"))
                .andExpect(status().isOk());

        verify(reviewControllerContract, times(1)).getReview(anyLong());
    }

    @Test
    void shouldNotReturnReviewWhenDoesntExists() throws Exception {
        long id = 999999L;
        Mockito.doThrow(new ItemNotFoundException("Review not found with id: " + id))
                .when(reviewControllerContract).getReview(id);

        mockMvc.perform(get("/api/v1/reviews/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnAllReviewsWhenExist() throws Exception {
        List<ReviewDTO> mockReviewDTOList = Arrays.asList(
                new ReviewDTO("johndoe1", "8511b204-06b2-4710-9d40-f8e2b61c701d", EnumRate.FIVE, "Great service!"),
                new ReviewDTO("johndoe2", "8511b204-06b2-4710-9d40-f8e2b61c701e", EnumRate.FOUR, "Good service!")
        );
        when(reviewControllerContract.getAllReviews()).thenReturn(mockReviewDTOList);

        mockMvc.perform(get("/api/v1/reviews"))
                .andExpect(status().isOk());

        verify(reviewControllerContract, times(1)).getAllReviews();
    }

    @Test
    void shouldReturnReviewsByUserIdWhenExist() throws Exception {
        List<ReviewDTO> mockReviewDTOList = Arrays.asList(
                new ReviewDTO("johndoe1", "8511b204-06b2-4710-9d40-f8e2b61c701d", EnumRate.FIVE, "Great service!"),
                new ReviewDTO("johndoe2", "8511b204-06b2-4710-9d40-f8e2b61c701e", EnumRate.FOUR, "Good service!")
        );
        when(reviewControllerContract.getReviewsByUserId(anyLong())).thenReturn(mockReviewDTOList);

        mockMvc.perform(get("/api/v1/reviews/user/1"))
                .andExpect(status().isOk());

        verify(reviewControllerContract, times(1)).getReviewsByUserId(anyLong());
    }

    @Test
    void shouldUpdateReviewWhenExists() throws Exception {
        ReviewUpdateRequest reviewUpdateRequest = new ReviewUpdateRequest(
                "Average service!",
                EnumRate.THREE
        );
        ReviewDTO mockReviewDTO = new ReviewDTO("johndoe", "8511b204-06b2-4710-9d40-f8e2b61c701d", EnumRate.THREE, "Average service!");

        String requestJson = new ObjectMapper().writeValueAsString(reviewUpdateRequest);

        Mockito.when(reviewControllerContract.updateReview(anyLong(), any(ReviewUpdateRequest.class))).thenReturn(mockReviewDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(reviewControllerContract, Mockito.times(1)).updateReview(anyLong(), any(ReviewUpdateRequest.class));
    }

    @Test
    void shouldDeleteReviewSuccessfully() throws Exception {
        long id = 1L;

        Mockito.doNothing().when(reviewControllerContract).deleteReview(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reviews/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(reviewControllerContract, Mockito.times(1)).deleteReview(id);
    }

    @Test
    void shouldHandleNonExistentReviewDelete() throws Exception {
        long id = 999999L;

        Mockito.doThrow(new ItemNotFoundException("Review not found with id: " + id))
                .when(reviewControllerContract).deleteReview(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reviews/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
