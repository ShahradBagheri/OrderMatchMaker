package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.MainServiceNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.exception.SubServiceTwoMainServiceException;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubServiceServiceImplTest {

    @Autowired
    private SubServiceServiceImpl subServiceService;

    @Autowired
    private MainServiceServiceImpl mainServiceService;

    @Test
    void subServiceShouldRegister(){

        SubService subService = SubService.builder()
                .name("registerTest")
                .build();

        subServiceService.register(subService);
        assertThat(subService.getId()).isNotNull();
    }

    @Test
    void duplicateSubServiceShouldNotSave(){

        SubService subService = SubService.builder()
                .name("duplicateName")
                .build();
        SubService dupSubService = SubService.builder()
                .name("duplicateName")
                .build();

        subServiceService.register(subService);
        subServiceService.register(dupSubService);
        assertThat(dupSubService.getId()).isNull();

    }

    @Test
    void subServiceShouldGetDeleted(){

        SubService subService = SubService.builder()
                .name("deleteTest")
                .build();

        subServiceService.register(subService);
        Long id = subService.getId();
        subServiceService.delete(subService);
        assertThatThrownBy( () -> subServiceService.findById(id) ).isInstanceOf(SubServiceNotFoundException.class);
    }

    @Test
    void subServiceShouldBeFound() throws SubServiceNotFoundException {
        SubService subService = SubService.builder()
                .name("findById test")
                .build();

        subServiceService.register(subService);
        assertThat(subServiceService.findById(subService.getId())).isNotNull();
    }

    @Test
    void mainServiceShouldNotGetFound(){

        SubService subService = SubService.builder()
                .name("should_not_found")
                .build();

        subServiceService.register(subService);
        Long id = subService.getId();
        subServiceService.delete(subService);
        assertThatThrownBy( () -> subServiceService.findById(id) ).isInstanceOf(SubServiceNotFoundException.class);
    }

    @Test
    void allMainServicesShouldBeFound(){
        SubService subService = SubService.builder()
                .name("add_to_all")
                .build();

        subServiceService.register(subService);
        assertThat(subServiceService.findAll()).isNotNull();
    }

    @Test
    void mainServiceShouldGetAdded() throws SubServiceTwoMainServiceException, SubServiceNotFoundException {
        SubService subService = SubService.builder()
                .name("testAddingMain")
                .build();

        subService = subServiceService.register(subService);

        MainService mainService = MainService.builder()
                .name("testAddingMain")
                .build();

        mainService = mainServiceService.register(mainService);

        subService = subServiceService.addMainService(subService,mainService);
        assertThat(subService.getMainService()).isNotNull();
    }

    @Test
    void canNotAddTwoMainServices() throws SubServiceTwoMainServiceException, SubServiceNotFoundException {
        SubService subService = SubService.builder()
                .name("testAddingDouble")
                .build();

        subService = subServiceService.register(subService);

        MainService mainService1 = MainService.builder()
                .name("testAddingDouble1")
                .build();

        mainService1 = mainServiceService.register(mainService1);

        MainService mainService2 = MainService.builder()
                .name("testAddingDouble2")
                .build();

        mainService2 = mainServiceService.register(mainService2);

        subServiceService.addMainService(subService,mainService1);
        SubService finalSubService = subService;
        MainService finalMainService = mainService2;
        assertThatThrownBy(() -> subServiceService.addMainService(finalSubService, finalMainService)).isInstanceOf(SubServiceTwoMainServiceException.class);
    }

}