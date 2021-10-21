package com.itrex.java.lab.entities;

public class Role {
    private Integer roleId;
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "\nRole{" +
                "roleId=" + roleId +
                ", roleName=" + roleName +
                "}";
    }
}
