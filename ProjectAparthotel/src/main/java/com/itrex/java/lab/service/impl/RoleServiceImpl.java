package com.itrex.java.lab.service.impl;

import com.itrex.java.lab.dto.RoleDTO;
import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.repositories.RoleRepository;
import com.itrex.java.lab.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> selectAll() {
        return roleRepository.selectAll().stream()
                .map(RoleServiceImpl::convertRoleEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO add(RoleDTO roleDTO) {
        return convertRoleEntityToDTO(roleRepository.add(convertRoleDTOToEntity(roleDTO)));
    }

    @Override
    public void addAll(List<RoleDTO> rolesDTO) {
        roleRepository.addAll(convertListDTOToRole(rolesDTO));
    }

    @Override
    public void delete(Integer roleId) {
        roleRepository.delete(roleId);
    }

    @Override
    public void update(RoleDTO roleDTO) {
        roleRepository.update(convertRoleDTOToEntity(roleDTO));
    }

    @Override
    public RoleDTO selectById(Integer roleId) {
        Role role = roleRepository.selectById(roleId);
        return convertRoleEntityToDTO(role);
    }

    public static RoleDTO convertRoleEntityToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(role.getRoleId());
        roleDTO.setRoleName(role.getRoleName());
        return roleDTO;
    }

    public static Role convertRoleDTOToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }

    private List<Role> convertListDTOToRole(List<RoleDTO> rolesDTO) {
        return rolesDTO.stream()
                .map(RoleServiceImpl::convertRoleDTOToEntity)
                .collect(Collectors.toList());
    }
}
