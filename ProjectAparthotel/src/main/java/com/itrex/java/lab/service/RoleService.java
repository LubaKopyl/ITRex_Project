package com.itrex.java.lab.service;

import com.itrex.java.lab.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> selectAll();

    RoleDTO add(RoleDTO roleDTO);

    void addAll(List<RoleDTO> rolesDTO);

    void delete(Integer roleId);

    void update(RoleDTO roleDTO);

    RoleDTO selectById(Integer roleId);
}
