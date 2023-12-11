package com.mslfox.oniipswingjpamvc;

import com.mslfox.oniipswingjpamvc.controllers.UsersCredentialsGUIController;
import com.mslfox.oniipswingjpamvc.model.Roles;
import com.mslfox.oniipswingjpamvc.model.UserCredentialDTO;
import com.mslfox.oniipswingjpamvc.model.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DemoAppCommandLineRunner implements CommandLineRunner {

    private final UsersCredentialsGUIController controller;
    private final UserServiceImpl userService;


    @Override
    public void run(String... args) {
        IntStream.rangeClosed(0, 25).forEach((i) -> {
            var user = UserCredentialDTO.builder()
                    .login("user" + i)
                    .password("password" + i)
                    .email("user" + i + "@example.com")
                    .ip("192.168.1." + i)
                    .role(Roles.values()[new Random().nextInt(0, 3)])
                    .build();
            userService.saveUser(user);
        });
        controller.start();
    }
}