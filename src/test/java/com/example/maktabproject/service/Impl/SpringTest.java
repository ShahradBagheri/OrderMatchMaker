package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.dto.user.UserFilterCriteriaDto;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringTest {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private SubServiceServiceImpl subServiceService;

    @Test
    void run() throws SubServiceNotFoundException {

        SubService byId = subServiceService.findById(1L);

        UserFilterCriteriaDto build = UserFilterCriteriaDto.builder()
                .email("z")
                .build();
        List<User> users = adminService.filterUsers(build);
        System.out.println(users.stream().map(User::getId).toList());
    }
}
