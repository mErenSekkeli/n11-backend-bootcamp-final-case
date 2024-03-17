package com.merensekkeli.customerreviewservice.faker;

import com.merensekkeli.customerreviewservice.dto.CompanyClientDTO;
import com.merensekkeli.customerreviewservice.enums.EnumStatus;

public class CompanyFaker {

    public static CompanyClientDTO fakeCompany() {
        String id = "1";
        String name = "Fake Company";
        String address = "123 Fake Street";
        String phone = "1234567890";
        String email = "fake@company.com";
        String website = "www.fakecompany.com";
        Double latitude = 40.712776;
        Double longitude = -74.005974;
        EnumStatus status = EnumStatus.ACTIVE;
        Double averageRate = 4.5;

        return new CompanyClientDTO(id, name, address, phone, email, website, latitude, longitude, status, averageRate);
    }
}
