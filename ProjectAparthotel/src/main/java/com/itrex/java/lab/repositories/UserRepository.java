package com.itrex.java.lab.repositories;

import com.itrex.java.lab.dto.UserInfo;
import com.itrex.java.lab.entities.User;

import java.util.List;

public interface UserRepository {
    List<User> selectAll();

    List<UserInfo> getUsersInfo();

    List<User> selectAllUsersByRole(String roleName);

    void add(User user);

    void addAll(List<User> users);

    void delete(Integer userId);

    void update(User user);

    User selectById(Integer userId);
}
