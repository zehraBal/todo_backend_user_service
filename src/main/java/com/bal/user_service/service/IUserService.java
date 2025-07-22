package com.bal.user_service.service;

import com.bal.user_service.dto.UserRequestDTO;
import com.bal.user_service.dto.UserResponseDTO;
import java.util.List;


public interface IUserService {

    UserResponseDTO getUserByUsername(String username);
    boolean userExists(String username);
    List<UserResponseDTO> getAllUsers();
    void deleteUser(String username);
    UserResponseDTO changeUsername(String currentUsername,String newUsername);
    UserResponseDTO updateUser(String username, UserRequestDTO userRequest);
}
