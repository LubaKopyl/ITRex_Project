package com.itrex.java.lab.repositories.impl;

import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.entities.User;
import com.itrex.java.lab.entities.UserRole;
import com.itrex.java.lab.repositories.UserRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class JDBCUserRepositoryImpl implements UserRepository {

    private static final String USER_ID_COLUMN = "user_Id";
    private static final String FIRST_NAME_COLUMN = "first_name";
    private static final String LAST_NAME_COLUMN = "last_name";
    private static final String EMAIL_COLUMN = "email";
    private static final String PASSWORD_COLUMN = "password";
    /*private static final String ROLE_ID_COLUMN = "role_Id";
    private static final String ROLE_NAME_COLUMN = "role_name";*/

    private static final String SELECT_ALL_QUERY = "SELECT * FROM users";
//    private static final String SELECT_ALL_USERS_WITH_ROLES_QUERY = "SELECT * FROM users INNER JOIN users_roles ON users.user_id = users_roles.user_id INNER JOIN roles ON users_roles.role_id = roles.role_id";
    private static final String SELECT_ALL_USERS_BY_ROLES_QUERY = "SELECT * FROM users INNER JOIN users_roles ON users.user_id = users_roles.user_id " +
        "INNER JOIN roles ON users_roles.role_id = roles.role_id WHERE role_name=?";
    private static final String INSERT_USER_QUERY = "INSERT INTO users(first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE user_id=?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET first_name=?, last_name=?, email=?, password=? WHERE user_id=?";
    private static final String SELECT_USER_BY_ID_QUERY = "SELECT * FROM users WHERE user_id=?";

    private final DataSource dataSource;

    public JDBCUserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement stm = con.createStatement();
             ResultSet resultSet = stm.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                User user = getUser(resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public List<User> selectAllUsersByRole(String roleName) {
        List<User> users = new ArrayList<>();
        try(Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_USERS_BY_ROLES_QUERY)) {
            preparedStatement.setString(1, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = getUser(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public void add(User user) {
        try (Connection con = dataSource.getConnection()) {
            insertUser(user, con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addAll(List<User> users) {
        try (Connection con = dataSource.getConnection()) {
            con.setAutoCommit(false);
            try {
                for (User user : users) {
                    insertUser(user, con);
                }
                con.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                con.rollback();
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User selectById(Integer userId) {
        User user = new User();
        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_ID_QUERY)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    private void insertUser(User user, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            final int effectiveRows = preparedStatement.executeUpdate();

            if (effectiveRows == 1) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setUserId(generatedKeys.getInt(USER_ID_COLUMN));
                    }
                }
            }
        }
    }

    private User getUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setUserId(resultSet.getInt(USER_ID_COLUMN));
            user.setFirstName(resultSet.getString(FIRST_NAME_COLUMN));
            user.setLastName(resultSet.getString(LAST_NAME_COLUMN));
            user.setEmail(resultSet.getString(EMAIL_COLUMN));
            user.setPassword(resultSet.getString(PASSWORD_COLUMN));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /*private Role getRole(ResultSet resultSet) {
        Role role = new Role();
        try {
            role.setRoleId(resultSet.getInt(ROLE_ID_COLUMN));
            role.setRoleName(resultSet.getString(ROLE_NAME_COLUMN));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    private UserRole getUserRole (ResultSet resultSet) {
        UserRole userRole = new UserRole();
        try {
            userRole.setUserId(resultSet.getInt(USER_ID_COLUMN));
            userRole.setRoleId(resultSet.getInt(ROLE_ID_COLUMN));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRole;
    }*/
}
