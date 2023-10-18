package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomerNotFoundException;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpertServiceImplSpringTest {

    @Autowired
    private ExpertServiceImpl expertService;

    @Test
    void validExpertRegisterShouldSave() {

        User user = User.builder()
                .firstname("shahrad")
                .lastname("bagheri")
                .email("expertShouldSave@gmaill.com")
                .password("qweasd123")
                .build();
        Expert expert = Expert.builder()
                .user(user)
                .build();

        expert = expertService.register(expert);
        assertThat(expert.getId()).isNotNull();
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
    void expertShouldBeFound() throws CustomerNotFoundException {
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

        assertThat(all).isNotNull();
    }

    @Test
    void CustomerShouldGetDeleted() throws CustomerNotFoundException {
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
        assertThatThrownBy(() -> expertService.findById(registerdExpert.getId())).isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void CustomerPasswordShouldChange() throws CustomerNotFoundException {
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
        expertService.changePassword(expert, "changed123");
        Expert changedExpert = expertService.findById(expert.getId());
        assertThat(changedExpert.getUser().getPassword()).isEqualTo("changed123");
    }

    @Test
    void CustomerFoundByUser() throws CustomerNotFoundException {
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
        Expert foundExpert = expertService.findByUser(expert.getUser());
        assertThat(expert.getUser()).isEqualTo(foundExpert.getUser());
    }
}