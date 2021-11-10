package com.itrex.java.lab.service;

import com.itrex.java.lab.dto.UserDTO;
import com.itrex.java.lab.dto.UserInfoDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> selectAll();

    List<UserInfoDTO> getUsersInfo();

    List<UserDTO> selectAllUsersByRole(String roleName);

    UserDTO add(UserDTO userDTO);

    void addAll(List<UserDTO> usersDTO);

    void delete(Integer userId);

    void update(UserDTO userDTO);

    UserDTO selectById(Integer userId);
}
