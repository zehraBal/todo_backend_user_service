package com.bal.user_service.service;

import com.bal.user_service.dto.LoginRequestDTO;
import com.bal.user_service.dto.UserRequestDTO;
import com.bal.user_service.dto.UserResponseDTO;

public interface IUserService {

    UserResponseDTO register(UserRequestDTO dto);
    UserResponseDTO login(LoginRequestDTO dto);
}
