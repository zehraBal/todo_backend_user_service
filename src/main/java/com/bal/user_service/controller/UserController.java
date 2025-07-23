package com.bal.user_service.controller;

import com.bal.user_service.dto.RegisterRequestDTO;
import com.bal.user_service.dto.UserRequestDTO;
import com.bal.user_service.dto.UserResponseDTO;
import com.bal.user_service.model.User;
import com.bal.user_service.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;


    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
    @GetMapping("/username/{username}/password-hash")
    public ResponseEntity<String> getUserPasswordHash(@PathVariable String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found"));

        return ResponseEntity.ok(user.getPassword());
    }

    @PostMapping("/save")
    ResponseEntity<UserResponseDTO> saveUser(@RequestBody RegisterRequestDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable String username,
            @RequestBody UserRequestDTO userRequest) {
        return ResponseEntity.ok(userService.updateUser(username, userRequest));
    }
    @PatchMapping("/{username}/change-username")
    public ResponseEntity<UserResponseDTO> changeUsername(
            @PathVariable String currentUsername,
            @RequestParam String newUsername) {
        return ResponseEntity.ok(userService.changeUsername(currentUsername, newUsername));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
