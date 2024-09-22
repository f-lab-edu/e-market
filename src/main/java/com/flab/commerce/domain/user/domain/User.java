package com.flab.commerce.domain.user.domain;


import com.flab.commerce.domain.user.dto.UserRequest.UserSignupRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * VO class 불변성을 위한 수정자 메서드 사용 x
 * <p>
 * Getter 접근자 메서드 생성을 위한 애노테이션
 * <p>
 * NoArgsConstructor default 생성자 생성을 위한 애노테이션
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private Long userId;

    private String username;

    private String email;

    private String password;

    private String phone;

    private String address;

    private LocalDateTime createdAt;

    @Builder
    public User(String username, String email, String password, String phone, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createdAt = LocalDateTime.now();
    }

    // static 메서드를 통한 인스턴스 생성
    public static User of(UserSignupRequest request, String password) {
        return new User(request.getUsername(), request.getEmail(), password,
            request.getPhone(), request.getAddress());
    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", createdAt=" + createdAt +
            '}';
    }
}
