package com.itrex.java.lab.repositories;

import com.itrex.java.lab.entities.Role;
import java.util.List;

public interface RoleRepository {
    List<Role> selectAll();
    void add(Role role);
    void addAll(List<Role> roles);
    void delete(Integer roleId);
    void update(Role role);
    Role selectById(Integer roleId);
}
