package com.itrex.java.lab.service.impl;

import com.itrex.java.lab.config.ApplicationContextConfiguration;
import com.itrex.java.lab.dto.UserDTO;
import com.itrex.java.lab.entities.User;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.exceptions.ServiceException;
import com.itrex.java.lab.repository.BaseRepositoryTest;
import com.itrex.java.lab.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceImplTest extends BaseRepositoryTest {

    private final ApplicationContext applicationContext;
    private final UserService userService;

    public UserServiceImplTest() {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        userService = applicationContext.getBean(UserService.class);
    }

    @Test
    public void selectAll_validData_shouldReturnTheNumberOfAllExistingUsers() throws ServiceException {
        //given
        int expectedNumberOfUsers = 3;
        //when
        int actualNumberOfUsers = userService.selectAll().size();
        //then
        assertEquals(expectedNumberOfUsers, actualNumberOfUsers);
    }

    @Test
    public void selectById_validData_shouldBeTheSame() throws ServiceException {
        //given
        UserDTO expectedUser = new UserDTO(1, "Luba", "Kopyl", "luba@gmail.com", "12345luba");
        //when
        UserDTO actualUser = userService.selectById(1);
        //then
        assertUserEquals(expectedUser, actualUser);
    }

    @Test
    public void selectAllUsersByRole_validData_shouldReturnTheNumberOfGuests() throws RepositoryException {
        //given
        int expectedUsersAmount = 2;
        //when
        List<UserDTO> actualUserAmount = userService.selectAllUsersByRole("guest");
        //then
        assertEquals(expectedUsersAmount, actualUserAmount.size());
    }

    @Test
    void add_validDate_shouldReturnNewUser() throws RepositoryException {
        //given
        UserDTO expectedUser = new UserDTO(4, "Anna", "Ivanova", "anna@gmail.com", "12345anna");
        //when
        UserDTO actualUser = userService.add(expectedUser);
        //then
        assertUserEquals(expectedUser, actualUser);
    }

    @Test
    void addAll_validDate_shouldReturnTheNumberOfUsers() throws RepositoryException {
        //given
        List<UserDTO> usersDTO = new ArrayList<>();
        UserDTO userDTO1 = new UserDTO(4, "Sasha", "Sergeev", "sasha@gmail.com", "12345sasha");
        usersDTO.add(userDTO1);
        UserDTO userDTO2 = new UserDTO(5, "Pavel", "Pavlov", "pavel@gmail.com", "12345pavel");
        usersDTO.add(userDTO2);

        int expectedUsersAmount = 5;
        //when
        userService.addAll(usersDTO);
        List<UserDTO> actualUsersAmount = userService.selectAll();
        //then
        assertEquals(expectedUsersAmount, actualUsersAmount.size());
    }

    @Test
    void update_validData_shouldReturnUpdatedUser() {
        //given
        UserDTO userDTO = new UserDTO(4, "Anna", "Ivanova", "anna@gmail.com", "12345anna");
        userDTO.setLastName("Kovaleva");
        String expectedLastName = "Kovaleva";
        // when
        userService.update(userDTO);
        String actualLastName = userDTO.getLastName();
        //then
        assertEquals(expectedLastName, actualLastName);
    }

    @Test
    void delete_validData_shouldReturnNewUsersAmount() {
        //given
        int expectedUsersAmount = 2;
        // when
        userService.delete(1);
        List<UserDTO> actualUsersAmount = userService.selectAll();
        //then
        assertEquals(expectedUsersAmount, actualUsersAmount.size());
    }

    private void assertUserEquals(UserDTO expectedUser, UserDTO actualUser) {
        assertEquals(expectedUser.getUserId(), actualUser.getUserId());
        assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
        assertEquals(expectedUser.getLastName(), actualUser.getLastName());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }


}
