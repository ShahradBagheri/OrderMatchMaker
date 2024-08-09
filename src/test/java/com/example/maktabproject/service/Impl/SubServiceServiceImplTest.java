package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        assertThatThrownBy( () -> subServiceService.findById(id) ).isInstanceOf(CustomExceptions.SubServiceNotFoundException.class);
    }

    @Test
    void subServiceShouldBeFound() throws CustomExceptions.SubServiceNotFoundException {
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
        assertThatThrownBy( () -> subServiceService.findById(id) ).isInstanceOf(CustomExceptions.SubServiceNotFoundException.class);
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
    void mainServiceShouldGetAdded() throws CustomExceptions.SubServiceTwoMainServiceException, CustomExceptions.SubServiceNotFoundException, CustomExceptions.MainServiceNotFoundException {
        SubService subService = SubService.builder()
                .name("testAddingMain")
                .build();

        subService = subServiceService.register(subService);

        MainService mainService = MainService.builder()
                .name("testAddingMain")
                .build();

        mainService = mainServiceService.register(mainService);

        subService = subServiceService.addMainService(subService.getId(),mainService.getId());
        assertThat(subService.getMainService()).isNotNull();
    }

    @Test
    void canNotAddTwoMainServices() throws CustomExceptions.SubServiceTwoMainServiceException, CustomExceptions.SubServiceNotFoundException, CustomExceptions.MainServiceNotFoundException {
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

        subServiceService.addMainService(subService.getId(),mainService1.getId());
        SubService finalSubService = subService;
        MainService finalMainService = mainService2;
        assertThatThrownBy(() -> subServiceService.addMainService(finalSubService.getId(), finalMainService.getId())).isInstanceOf(CustomExceptions.SubServiceTwoMainServiceException.class);
    }

    @Test
    void mainServiceShouldGetRemoved() throws CustomExceptions.SubServiceTwoMainServiceException, CustomExceptions.SubServiceNotFoundException, CustomExceptions.MainServiceNotFoundException {
        SubService subService = SubService.builder()
                .name("testRemoveMain")
                .build();

        subService = subServiceService.register(subService);

        MainService mainService = MainService.builder()
                .name("testRemoveMain")
                .build();

        mainService = mainServiceService.register(mainService);

        subService = subServiceService.addMainService(subService.getId(),mainService.getId());
        subService = subServiceService.removeMainService(subService.getId());
        assertThat(subService.getMainService()).isNull();
    }

    @Test
    void removeNullMainServiceShouldThrow() throws CustomExceptions.SubServiceTwoMainServiceException, CustomExceptions.SubServiceNotFoundException, CustomExceptions.MainServiceNotFoundException {

        SubService subService = SubService.builder()
                .name("testRemoveMainNull")
                .build();

        subService = subServiceService.register(subService);
        SubService finalSubService = subService;
        assertThatThrownBy(() -> subServiceService.removeMainService(finalSubService.getId())).isInstanceOf(CustomExceptions.MainServiceNotFoundException.class);
    }

    @Test
    void shouldFindAllByMainService() throws CustomExceptions.MainServiceNotFoundException {

        MainService mainService1 = MainService.builder()
                .name("testingFindAllByMainService1")
                .build();

        mainService1 = mainServiceService.register(mainService1);

        MainService mainService2 = MainService.builder()
                .name("testingFindAllByMainService2")
                .build();

        mainService2 = mainServiceService.register(mainService2);

        SubService subService1 = SubService.builder()
                .name("testingAll1")
                .mainService(mainService1)
                .build();

        subService1 = subServiceService.register(subService1);

        SubService subService2 = SubService.builder()
                .name("testingAll2")
                .mainService(mainService2)
                .build();

        subService2 = subServiceService.register(subService2);

        SubService subService3 = SubService.builder()
                .name("testingAll3")
                .mainService(mainService1)
                .build();

        subService3 = subServiceService.register(subService3);

        List<SubService> byMainService = subServiceService.findByMainService(mainService1.getId());
        assertThat(byMainService.size()).isGreaterThan(1);
    }

}