package com.itrex.java.lab.repositories.impl.jdbc;

import com.itrex.java.lab.dto.UserInfo;
import com.itrex.java.lab.entities.User;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.QueryConstants;
import com.itrex.java.lab.repositories.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserRepositoryImpl implements UserRepository {

    private final DataSource dataSource;

    public JDBCUserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> selectAll() throws RepositoryException {
        List<User> users = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement stm = con.createStatement();
             ResultSet resultSet = stm.executeQuery(QueryConstants.SELECT_ALL_USERS_QUERY)) {
            while (resultSet.next()) {
                User user = getUser(resultSet);
                users.add(user);
            }
        } catch (SQLException ex) {
            throw new RepositoryException("Can't get a list of users." , ex);
        }
        return users;
    }

    @Override
    public List<UserInfo> getUsersInfo() {
        List<UserInfo> userInfos = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement stm = con.createStatement();
             ResultSet resultSet = stm.executeQuery(QueryConstants.SELECT_ALL_USERS_WITH_ROLES_QUERY)) {
            while (resultSet.next()) {
                String firstName = resultSet.getString(QueryConstants.FIRST_NAME_COLUMN);
                String lastName = resultSet.getString(QueryConstants.LAST_NAME_COLUMN);
                String roleName = resultSet.getString(QueryConstants.ROLE_NAME_COLUMN);
                userInfos.add(new UserInfo(firstName, lastName, roleName));
            }
        } catch (SQLException ex) {
            throw new RepositoryException("Can't get users info." , ex);
        }
        return userInfos;
    }

    @Override
    public List<User> selectAllUsersByRole(String roleName) {
        List<User> users = new ArrayList<>();
        try(Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(QueryConstants.SELECT_ALL_USERS_BY_ROLES_QUERY)) {
            preparedStatement.setString(1, roleName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = getUser(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            throw new RepositoryException("Can't get a list of users by role." , ex);
        }
        return users;
    }

    @Override
    public User add(User user) {
        try (Connection con = dataSource.getConnection()) {
            insertUser(user, con);
        } catch (SQLException ex) {
            throw new RepositoryException("Can't add a user." , ex);
        }
        return user;
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
                con.rollback();
                throw new RepositoryException("Can't add a list of users." , ex);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            throw new RepositoryException("Can't add a list of users." , ex);
        }
    }

    @Override
    public void delete(Integer userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_USER_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException("Can't delete a user." , ex);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.UPDATE_USER_QUERY)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException("Can't update a user." , ex);
        }
    }

    @Override
    public User selectById(Integer userId) {
        User user = new User();
        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(QueryConstants.SELECT_USER_BY_ID_QUERY)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (SQLException ex) {
            throw new RepositoryException("Can't get a user by id." , ex);
        }
        return user;
    }

    private void insertUser(User user, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(QueryConstants.INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            final int effectiveRows = preparedStatement.executeUpdate();

            if (effectiveRows == 1) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setUserId(generatedKeys.getInt(QueryConstants.USER_ID_COLUMN));
                    }
                }
            }
        }
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt(QueryConstants.USER_ID_COLUMN));
        user.setFirstName(resultSet.getString(QueryConstants.FIRST_NAME_COLUMN));
        user.setLastName(resultSet.getString(QueryConstants.LAST_NAME_COLUMN));
        user.setEmail(resultSet.getString(QueryConstants.EMAIL_COLUMN));
        user.setPassword(resultSet.getString(QueryConstants.PASSWORD_COLUMN));
        return user;
    }
}
