package com.bal.user_service.service.impl;

import com.bal.user_service.dto.UserRequestDTO;
import com.bal.user_service.dto.UserResponseDTO;
import com.bal.user_service.model.User;
import com.bal.user_service.repository.UserRepository;
import com.bal.user_service.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private User dtoToUser(UserRequestDTO dto){
        return modelMapper.map(dto,User.class);
    }

    private UserResponseDTO userToResponseDto(User user){
        return modelMapper.map(user,UserResponseDTO.class);
    }


    @Override
    public UserResponseDTO getUserByUsername(String username) {
        User user=userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("user not found by this email"));
        return userToResponseDto(user);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::userToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found"));

        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO changeUsername(String currentUsername, String newUsername) {
        if(userExists(newUsername)){
            throw new RuntimeException("this username  already exist");
        }
        User user=userRepository.findByUsername(currentUsername).orElseThrow(()-> new RuntimeException("user not found"));
        user.setUsername(newUsername);
        User updatedUser=userRepository.save(user);
        return userToResponseDto(updatedUser);

    }

    @Override
    public UserResponseDTO updateUser(String username, UserRequestDTO userRequest) {
        User existingUser=userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("user not found"));
        modelMapper.map(userRequest,existingUser);
        User updatedUser=userRepository.save(existingUser);
        return userToResponseDto(updatedUser);
    }
}
