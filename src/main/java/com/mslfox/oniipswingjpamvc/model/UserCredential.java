package com.mslfox.oniipswingjpamvc.model;


import com.mslfox.oniipswingjpamvc.utility.IPv4Validator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users_credentials")
public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @Size(min = 5, message = "Login must be at least 5 characters long")
    private String login;
    @Column
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @Column
    @Email(message = "Invalid email format")
    private String email;
    @Column
    @Pattern(regexp = IPv4Validator.IPV4_PATTERN, message = "Invalid IP address format")
    private String ip;
    @Column
    private final LocalDateTime creationDate = LocalDateTime.now();
    @Column
    @NonNull
    private LocalDateTime editDate = LocalDateTime.now();
    @NonNull
    @Column(name = "accessLevel")
    private Roles role;
}
