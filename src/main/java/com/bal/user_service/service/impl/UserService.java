package com.bal.user_service.service.impl;

import com.bal.user_service.dto.LoginRequestDTO;
import com.bal.user_service.dto.UserRequestDTO;
import com.bal.user_service.dto.UserResponseDTO;
import com.bal.user_service.model.User;
import com.bal.user_service.repository.UserRepository;
import com.bal.user_service.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDTO register(UserRequestDTO dto) {
      if(userRepository.existsByEmail(dto.getEmail())){
          throw new RuntimeException("User already exist");
      }

      User user=modelMapper.map(dto,User.class);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRoles(List.of("USER"));

      User savedUser=userRepository.save(user);

      return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO dto) {
        User user=userRepository.findByUsername(dto.getUsername())
                .orElseThrow(()->new RuntimeException("User not found"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        return modelMapper.map(user, UserResponseDTO.class);
    }
}
