package com.mslfox.oniipswingjpamvc;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OniipSwingJpaMvcApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OniipSwingJpaMvcApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
