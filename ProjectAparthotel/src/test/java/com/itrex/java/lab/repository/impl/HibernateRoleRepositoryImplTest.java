package com.itrex.java.lab.repository.impl;

import com.itrex.java.lab.config.ApplicationContextConfiguration;
import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.RoleRepository;
import com.itrex.java.lab.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateRoleRepositoryImplTest extends BaseRepositoryTest {

    private final ApplicationContext applicationContext;
    private final RoleRepository roleRepository;

    public HibernateRoleRepositoryImplTest() {
        super();
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        roleRepository = applicationContext.getBean(RoleRepository.class);
    }

    @Test
    public void selectAll_validData_shouldReturnTheNumberOfAllExistingRoles() throws RepositoryException {
        //given
        int expectedNumberOfRoles = 2;
        //when
        int actualNumberOfRoles = roleRepository.selectAll().size();
        //then
        assertEquals(expectedNumberOfRoles, actualNumberOfRoles);
    }

    @Test
    public void selectById_validData_shouldBeTheSame() throws RepositoryException {
        //given
        Role expectedRole = new Role(1, "admin");
        //when
        Role actualRole = roleRepository.selectById(1);
        //then
        assertRoleEquals(expectedRole, actualRole);
    }

    @Test
    void add_validDate_shouldReturnNewRole() throws RepositoryException {
        //given
        Role expectedRole = new Role(1, "admin1");
        //when
        Role actualRole = roleRepository.add(expectedRole);
        //then
        assertRoleEquals(expectedRole, actualRole);
    }

    @Test
    void delete_validData_shouldReturnNewRolesAmount() {
        //given
        int expectedRolesAmount = 1;
        // when
        roleRepository.delete(1);
        List<Role> actualRolesAmount = roleRepository.selectAll();
        //then
        assertEquals(expectedRolesAmount, actualRolesAmount.size());
    }

    private void assertRoleEquals(Role expectedRole, Role actualRole) {
        assertEquals(expectedRole.getRoleId(), actualRole.getRoleId());
        assertEquals(expectedRole.getRoleName(), actualRole.getRoleName());
    }

}
