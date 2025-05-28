package com.entbooking.eticketing.dto.request;

import com.entbooking.eticketing.enums.Gender;
import lombok.Data;

@Data
public class UserEntryDto {

    private String name;
    private Integer age;
    private String address;
    private String mobileNo;
    private String emailId;
    private Gender gender;
}
