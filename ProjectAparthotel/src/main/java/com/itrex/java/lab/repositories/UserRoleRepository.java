package com.itrex.java.lab.repositories;

import com.itrex.java.lab.entities.UserRole;
import java.util.List;

public interface UserRoleRepository {
    List<UserRole> selectAll();
    void add(UserRole userRole);
    void addAll(List<UserRole> userRoles);
    void delete(Integer userRoleId);
    void update(UserRole userRole);
    UserRole selectById(Integer userRoleId);
}
