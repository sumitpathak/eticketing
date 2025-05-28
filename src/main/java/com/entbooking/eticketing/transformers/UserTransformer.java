package com.entbooking.eticketing.transformers;


import com.entbooking.eticketing.dto.request.UserEntryDto;
import com.entbooking.eticketing.dto.response.ReturnUserDto;
import com.entbooking.eticketing.entity.User;

public class UserTransformer {

    public static User userDtoToUser(UserEntryDto userEntryDto) {

        return User.builder()
                .name(userEntryDto.getName())
                .age(userEntryDto.getAge())
                .address(userEntryDto.getAddress())
                .gender(userEntryDto.getGender())
                .mobileNo(userEntryDto.getMobileNo())
                .emailId(userEntryDto.getEmailId())
                .build();
    }

    public static ReturnUserDto userToUserDto(User user) {

        return ReturnUserDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .address(user.getAddress())
                .gender(user.getGender())
                .build();
    }
}
