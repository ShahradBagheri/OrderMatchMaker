package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import com.example.maktabproject.util.ImageProcessing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ExpertServiceImplSpringTest {

    @Autowired
    private ExpertServiceImpl expertService;

    @Autowired
    private SubServiceServiceImpl subServiceService;

    @Autowired
    private ImageProcessing imageProcessing;

    @Test
    void validExpertRegisterShouldSave() throws CustomExceptions.ImageToBigException {

        /*byte[] bytes = imageProcessing.imageToBytes("src/main/resources/pictures/Screenshot_20231020_092009.png");

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("expertShouldSave@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .imageData(bytes)
                .build();

        expert = expertService.register(expert);
        assertThat(expert.getId()).isNotNull();*/
    }

    @Test
    void duplicateEmailShouldNotSave() {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("expertfirst@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        Expert dupExpert = Expert.builder()
                .user(user)
                .build();

        expertService.register(expert);
        expertService.register(dupExpert);
        assertThat(dupExpert.getId()).isNull();
    }

    @Test
    void invalidPasswordShouldNotSave() {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("somethingasdasdasd@gmaill.com")
                .password("qweasdqwe")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expertService.register(expert);
        assertThat(expert.getId()).isNull();
    }

    @Test
    void invalidEmailShouldNotSave() {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahrad2gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expertService.register(expert);
        assertThat(expert.getId()).isNull();
    }

    @Test
    void expertShouldBeFound() throws CustomExceptions.ExpertNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahradFindExpert@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expertService.register(expert);
        Expert foundExpert = expertService.findById(expert.getId());

        assertThat(foundExpert).isNotNull();
    }

    @Test
    void customersShouldBeFound() {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahradTestingAllExpert@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expertService.register(expert);

        List<Expert> all = expertService.findAll();

        assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    void customerShouldGetDeleted() throws CustomExceptions.ExpertNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahradexpertdelete@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        Expert registerdExpert = expertService.register(expert);
        expertService.delete(expertService.findById(registerdExpert.getId()));
        assertThatThrownBy(() -> expertService.findById(registerdExpert.getId())).isInstanceOf(CustomExceptions.ExpertNotFoundException.class);
    }

    @Test
    void customerPasswordShouldChange() throws CustomExceptions.ExpertNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("shahradchangedexpertpassword@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);
        expertService.changePassword(expert.getId(), "changed123");
        Expert changedExpert = expertService.findById(expert.getId());
        assertThat(changedExpert.getUser().getPassword()).isEqualTo("changed123");
    }

    @Test
    void customerFoundByUser() throws CustomExceptions.ExpertNotFoundException {
        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("findUserTestExpert@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);
        Expert foundExpert = expertService.findByUser(expert.getUser().getId());
        assertThat(expert.getUser()).isEqualTo(foundExpert.getUser());
    }

    @Test
    void statusShouldUpdate() throws CustomExceptions.ExpertNotFoundException {
//        User user = User.builder()
//                .firstname("shahrad")
//                .lastname("bagheri")
//                .email("statusUpdate@gmaill.com")
//                .password("qweasd123")
//                .build();
//        Expert expert = Expert.builder()
//                .user(user)
//                .expertStatus(ExpertStatus.NEW)
//                .build();
//        expert = expertService.register(expert);
//        ExpertStatus updatedStatus = ExpertStatus.APPROVED;
//
//        expert = expertService.updateStatus(expert.getId(),updatedStatus);
//        assertThat(expert.getExpertStatus()).isEqualTo(updatedStatus);
    }

    @Test
    void subServiceShouldGetAdded() throws CustomExceptions.ExpertNotFoundException, CustomExceptions.SubServiceNotFoundException {
//
//        User user = User.builder()
//                .firstname("shahrad")
//                .lastname("bagheri")
//                .email("addSubService@gmaill.com")
//                .password("qweasd123")
//                .build();
//        Expert expert = Expert.builder()
//                .user(user)
//                .expertStatus(ExpertStatus.NEW)
//                .build();
//        expert = expertService.register(expert);
//
//        SubService subService = SubService.builder()
//                .name("addingSubToExpertTest")
//                .build();
//
//        subService = subServiceService.register(subService);
//
//
//        expert = expertService.addSubService(expert.getId(),subService.getId());
//        assertThat(expert.getSubServices()).isNotNull();
    }

    @Test
    void addSecondSubServiceToGetAdded() throws CustomExceptions.ExpertNotFoundException, CustomExceptions.SubServiceNotFoundException {
//
//        User user = User.builder()
//                .firstname("shahrad")
//                .lastname("bagheri")
//                .email("addingMoreThanOne@gmaill.com")
//                .password("qweasd123")
//                .build();
//        Expert expert = Expert.builder()
//                .user(user)
//                .expertStatus(ExpertStatus.NEW)
//                .build();
//        expert = expertService.register(expert);
//
//        SubService subService = SubService.builder()
//                .name("firstOneToGetAdded")
//                .build();
//
//        subService = subServiceService.register(subService);
//
//        SubService subService2 = SubService.builder()
//                .name("secondOneToGetAdded")
//                .build();
//
//        subService2 = subServiceService.register(subService2);
//
//
//        expert = expertService.addSubService(expert.getId(),subService.getId());
//        expert = expertService.addSubService(expert.getId(),subService2.getId());
//
//        assertThat(expert.getSubServices()).hasSize(2);
    }
}