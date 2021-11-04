package com.itrex.java.lab.repository.impl;

import com.itrex.java.lab.config.ApplicationContextConfiguration;
import com.itrex.java.lab.entities.User;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.UserRepository;
import com.itrex.java.lab.repository.BaseRepositoryTest;
import com.itrex.java.lab.service.FlywayService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateUserRepositoryImplTest extends BaseRepositoryTest {

    private final ApplicationContext applicationContext;
    private final FlywayService flywayService;
    private final UserRepository userRepository;

    public HibernateUserRepositoryImplTest() {
        super();
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        flywayService = applicationContext.getBean(FlywayService.class);
        userRepository = applicationContext.getBean(UserRepository.class);
    }

    @Test
    public void selectAll_validData_shouldReturnTheNumberOfAllExistingUsers() throws RepositoryException {
        //given
        int expectedNumberOfUsers = 3;
        //when
        int actualNumberOfUsers = userRepository.selectAll().size();
        //then
        assertEquals(expectedNumberOfUsers, actualNumberOfUsers);
    }

    @Test
    public void selectById_validData_shouldBeTheSame() throws RepositoryException {
        //given
        User expectedUser = new User(1, "Luba", "Kopyl", "luba@gmail.com", "12345luba");
        //when
        User actualUser = userRepository.selectById(1);
        //then
        assertUserEquals(expectedUser, actualUser);
    }

    @Test
    public void selectAllUsersByRole_validData_shouldReturnTheNumberOfGuests() throws RepositoryException {
        //given
        int expectedUsersAmount = 2;
        //when
        List<User> actualUserAmount = userRepository.selectAllUsersByRole("guest");
        //then
        assertEquals(expectedUsersAmount, actualUserAmount.size());
    }

    @Test
    void add_validDate_shouldReturnNewUser() throws RepositoryException {
        //given
        User expectedUser = new User(4, "Anna", "Ivanova", "anna@gmail.com", "12345anna");
        //when
        User actualUser = userRepository.add(expectedUser);
        //then
        assertUserEquals(expectedUser, actualUser);
    }

    @Test
    void addAll_validDate_shouldReturnTheNumberOfUsers() throws RepositoryException {
        //given
        List<User> users = new ArrayList<>();
        User user1 = new User(4, "Sasha", "Sergeev", "sasha@gmail.com", "12345sasha");
        users.add(user1);
        User user2 = new User(5, "Pavel", "Pavlov", "pavel@gmail.com", "12345pavel");
        users.add(user2);

        int expectedUsersAmount = 5;
        //when
        userRepository.addAll(users);
        List<User> actualUsersAmount = userRepository.selectAll();
        //then
        assertEquals(expectedUsersAmount, actualUsersAmount.size());
    }

    @Test
    void update_validData_shouldReturnUpdatedUser() {
        //given
        User user = new User(4, "Anna", "Ivanova", "anna@gmail.com", "12345anna");
        user.setLastName("Kovaleva");
        String expectedLastName = "Kovaleva";
        // when
        userRepository.update(user);
        String actualLastName = user.getLastName();
        //then
        assertEquals(expectedLastName, actualLastName);
    }

    @Test
    void delete_validData_shouldReturnNewUsersAmount() {
        //given
        int expectedUsersAmount = 2;
        // when
        userRepository.delete(1);
        List<User> actualUsersAmount = userRepository.selectAll();
        //then
        assertEquals(expectedUsersAmount, actualUsersAmount.size());
    }

    private void assertUserEquals(User expectedUser, User actualUser) {
        assertEquals(expectedUser.getUserId(), actualUser.getUserId());
        assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
        assertEquals(expectedUser.getLastName(), actualUser.getLastName());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }
}