package com.flab.commerce.domain.user.service;

import com.flab.commerce.domain.user.domain.User;
import com.flab.commerce.domain.user.dto.UserRequest.UserSignupRequest;
import com.flab.commerce.domain.user.repository.UserRepository;
import com.flab.commerce.domain.user.service.encryption.EncryptionService;
import com.flab.commerce.global.common.CommonResponse;
import com.flab.commerce.global.error.CommonException;
import com.flab.commerce.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository = UserRepository.getInstance();
    private final EncryptionService encoder;


    public void signUp(UserSignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CommonException(ErrorCode.DUPLICATED_EMAIL);
        }

        User user = User.of(request, encoder.encryptPassword(request.getEmail(),
            request.getPassword()));
        userRepository.insertUser(user);
    }

    public CommonResponse<User> getUser(Long userId) {
        User user = userRepository.getUser(1);
        if (user == null) {
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }
        return CommonResponse.success(user);
    }
}
