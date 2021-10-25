package com.itrex.java.lab.repositories;

public class QueryConstants {

    public static final String USER_ID_COLUMN = "user_Id";
    public static final String FIRST_NAME_COLUMN = "first_name";
    public static final String LAST_NAME_COLUMN = "last_name";
    public static final String EMAIL_COLUMN = "email";
    public static final String PASSWORD_COLUMN = "password";

    public static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM users";
    public static final String SELECT_ALL_USERS_WITH_ROLES_QUERY = "SELECT users.first_name, users.last_name, roles.role_name FROM users " +
            "INNER JOIN users_roles ON users.user_id = users_roles.user_id INNER JOIN roles ON users_roles.role_id = roles.role_id";
    public static final String SELECT_ALL_USERS_BY_ROLES_QUERY = "SELECT * FROM users INNER JOIN users_roles ON users.user_id = users_roles.user_id " +
            "INNER JOIN roles ON users_roles.role_id = roles.role_id WHERE role_name = ?";
    public static final String INSERT_USER_QUERY = "INSERT INTO users(first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    public static final String DELETE_USER_QUERY = "DELETE FROM users WHERE user_id = ?";
    /*public static final String DELETE_USER_QUERY = "DELETE FROM users u INNER JOIN users_roles ur ON u.user_id = ur.user_id " +
            "INNER JOIN roles r ON ur.role_id = r.role_id WHERE user_id = ?";*/
    public static final String UPDATE_USER_QUERY = "UPDATE users SET first_name=?, last_name=?, email=?, password=? WHERE user_id = ?";
    public static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM users WHERE user_id = ?";


    public static final String ROLE_ID_COLUMN = "role_Id";
    public static final String ROLE_NAME_COLUMN = "role_name";

    public static final String SELECT_ALL_ROLES_QUERY = "SELECT * FROM roles";
    public static final String INSERT_ROLE_QUERY = "INSERT INTO roles(role_name) VALUES (?)";
    public static final String DELETE_ROLE_QUERY = "DELETE FROM roles WHERE role_id=?";
    public static final String UPDATE_ROLE_QUERY = "UPDATE roles SET role_name=? WHERE role_id=?";
    public static final String SELECT_ROLE_BY_ID_QUERY = "SELECT * FROM roles WHERE role_id=?";
}
