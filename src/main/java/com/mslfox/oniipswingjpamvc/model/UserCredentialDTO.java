package com.mslfox.oniipswingjpamvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
public class UserCredentialDTO {
    private long id;
    private String login;
    private String password;
    private String email;
    private String ip;
    private LocalDateTime creationDate;
    private LocalDateTime editDate;
    private Roles role;

    public UserCredentialDTO() {
        this.id = -1L;
        this.login = "";
        this.password = "";
        this.email = "";
        this.ip = "";
        this.creationDate = null;
        this.editDate = null;
        this.role = Roles.NO_ROLE;
    }
}

