package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.MainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class MainServiceServiceImplTest {

    @Autowired
    private MainServiceServiceImpl mainServiceService;

    @Test
    void mainServiceShouldRegister(){

        MainService mainService = MainService.builder()
                .name("registerTest")
                .build();

        mainServiceService.register(mainService);
        assertThat(mainService.getId()).isNotNull();
    }

    @Test
    void duplicateMainServiceShouldNotSave(){

        MainService mainService = MainService.builder()
                .name("duplicateName")
                .build();
        MainService dupMainService = MainService.builder()
                .name("duplicateName")
                .build();

        mainServiceService.register(mainService);
        mainServiceService.register(dupMainService);
        assertThat(dupMainService.getId()).isNull();

    }

    @Test
    void mainServiceShouldGetDeleted(){

        MainService mainService = MainService.builder()
                .name("deleteTest")
                .build();

        mainServiceService.register(mainService);
        Long id = mainService.getId();
        mainServiceService.delete(mainService);
        assertThatThrownBy( () -> mainServiceService.findById(id) ).isInstanceOf(CustomExceptions.MainServiceNotFoundException.class);
    }

    @Test
    void mainServiceShouldBeFound() throws CustomExceptions.MainServiceNotFoundException {
        MainService mainService = MainService.builder()
                .name("findById test")
                .build();

        mainServiceService.register(mainService);
        assertThat(mainServiceService.findById(mainService.getId())).isNotNull();
    }

    @Test
    void mainServiceShouldNotGetFound(){

        MainService mainService = MainService.builder()
                .name("should_not_found")
                .build();

        mainServiceService.register(mainService);
        Long id = mainService.getId();
        mainServiceService.delete(mainService);
        assertThatThrownBy( () -> mainServiceService.findById(id) ).isInstanceOf(CustomExceptions.MainServiceNotFoundException.class);
    }

    @Test
    void allMainServicesShouldBeFound(){
        MainService mainService = MainService.builder()
                .name("add_to_all")
                .build();

        mainServiceService.register(mainService);
        assertThat(mainServiceService.findAll().size()).isGreaterThan(0);
    }
}