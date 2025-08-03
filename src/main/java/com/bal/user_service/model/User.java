package com.bal.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="users")
//@Document(collection = "users")
// @Document --> Specifies that a Java class will map to a collection in MongoDB
// A class that receives this annotation represents an entity to be stored in MongoDB
// if collection parameter is not specified, Spring uses the class name in lowercase(User-->user)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private List<String> roles;

}
