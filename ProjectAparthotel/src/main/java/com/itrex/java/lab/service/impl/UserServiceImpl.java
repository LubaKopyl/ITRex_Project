package com.itrex.java.lab.service.impl;

import com.itrex.java.lab.dto.UserDTO;
import com.itrex.java.lab.dto.UserInfoDTO;
import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.entities.User;
import com.itrex.java.lab.repositories.UserRepository;
import com.itrex.java.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDTO> selectAll() {
        return userRepository.selectAll().stream()
                .map(UserServiceImpl::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserInfoDTO> getUsersInfo() {
        return null;
    }

    @Override
    public List<UserDTO> selectAllUsersByRole(String roleName) {
        return userRepository.selectAllUsersByRole(roleName).stream()
                .map(UserServiceImpl::convertUserEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO add(UserDTO userDTO) {
        return convertUserEntityToDTO(userRepository.add(convertUserDTOToEntity(userDTO)));
    }

    @Override
    public void addAll(List<UserDTO> usersDTO) {
        userRepository.addAll(convertListDTOToUser(usersDTO));
    }

    @Override
    public void delete(Integer userId) {
        userRepository.delete(userId);
    }

    @Override
    public void update(UserDTO userDTO) {
        userRepository.update(convertUserDTOToEntity(userDTO));
    }

    @Override
    public UserDTO selectById(Integer userId) {
        User user = userRepository.selectById(userId);
        return convertUserEntityToDTO(user);
    }

    public static UserDTO convertUserEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public static User convertUserDTOToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public static UserInfoDTO convertUserInfoToDTO(User user, Role role) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setFirstName(user.getFirstName());
        userInfoDTO.setLastName(user.getLastName());
        userInfoDTO.setRoleName(role.getRoleName());
        return userInfoDTO;
    }

    private List<User> convertListDTOToUser(List<UserDTO> usersDTO) {
        return usersDTO.stream()
                .map(UserServiceImpl::convertUserDTOToEntity)
                .collect(Collectors.toList());
    }
}
