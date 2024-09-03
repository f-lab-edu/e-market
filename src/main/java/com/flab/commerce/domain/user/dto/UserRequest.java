package com.flab.commerce.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;


public class UserRequest {

    @Getter
    @AllArgsConstructor
    public static class UserSignupRequest {

        @Email
        @NotBlank(message = "email은 필수 입력값 입니다.")
        private String email;

        @NotBlank(message = "비밀 번호를 입력해주세요")
        @Pattern(regexp = "^[a-z]{8,20}$\n", message = "소문자 8 ~ 20의 문자를 입력해주세요")
        private String password;

        @NotBlank(message = "이름은 필수 입력값 입니다.")
        private String username;

        @NotBlank(message = "연락처는 필수 입력값 입니다.")
        private String phone;

        @NotBlank(message = "주소지 미입력시 원활한 서비스 이용이 어렵습니다.")
        private String address;
    }
}
