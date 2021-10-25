package com.itrex.java.lab.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_roles", schema = "public")

public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "\nUserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                "}";
    }
}
