package com.itrex.java.lab.repository.impl;

import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.RoleRepository;
import com.itrex.java.lab.repositories.impl.hibernate.HibernateRoleRepositoryImpl;
import com.itrex.java.lab.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateRoleRepositoryImplTest extends BaseRepositoryTest {

    private final RoleRepository roleRepository;

    public HibernateRoleRepositoryImplTest() {
        super();
        roleRepository = new HibernateRoleRepositoryImpl(getSessionFactory().openSession());
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
