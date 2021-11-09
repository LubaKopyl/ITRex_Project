package com.itrex.java.lab.repositories;

import com.itrex.java.lab.dto.UserInfoDTO;
import com.itrex.java.lab.entities.User;
import java.util.List;

public interface UserRepository {
    List<User> selectAll();

    List<UserInfoDTO> getUsersInfo();

    List<User> selectAllUsersByRole(String roleName);

    User add(User user);

    void addAll(List<User> users);

    void delete(Integer userId);

    void update(User user);

    User selectById(Integer userId);
}
