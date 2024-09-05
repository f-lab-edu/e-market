package com.flab.commerce.domain.user.controller;

import com.flab.commerce.domain.user.domain.User;
import com.flab.commerce.domain.user.dto.UserRequest.UserLoginRequest;
import com.flab.commerce.domain.user.dto.UserRequest.UserSignupRequest;
import com.flab.commerce.domain.user.service.UserService;
import com.flab.commerce.global.common.CommonResponse;
import com.flab.commerce.global.common.annotation.CheckUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/v1")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody UserSignupRequest request) {
        userService.signUp(request);
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginRequest request) {
        userService.login(request);
    }

    @DeleteMapping("/logout")
    public void logout(@CheckUserId Long userId) {
        userService.logout(userId);
    }

    @GetMapping
    public CommonResponse<User> getUser(@RequestParam(name = "userId") Long userId) {
        return userService.getUser(userId);
    }

}
