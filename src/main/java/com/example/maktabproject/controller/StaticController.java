package com.example.maktabproject.controller;

import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/static")
public class StaticController {

    @GetMapping("/login")
    @SneakyThrows
    public Resource getLogin(){
        Path path = Paths.get(Objects.requireNonNull(getClass().getResource("/static/login.html")).toURI());
        return new ByteArrayResource(Files.readAllBytes(path));
    }
}
