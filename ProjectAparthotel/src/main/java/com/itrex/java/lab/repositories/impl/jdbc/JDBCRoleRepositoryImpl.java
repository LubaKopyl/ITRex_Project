package com.itrex.java.lab.repositories.impl.jdbc;

import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.QueryConstants;
import com.itrex.java.lab.repositories.RoleRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCRoleRepositoryImpl implements RoleRepository {

    private final DataSource dataSource;

    public JDBCRoleRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Role> selectAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement stm = con.createStatement();
             ResultSet resultSet = stm.executeQuery(QueryConstants.SELECT_ALL_ROLES_QUERY)) {
            while (resultSet.next()) {
                Role role = getRole(resultSet);
                roles.add(role);
            }
        } catch (SQLException ex) {
            throw new RepositoryException("Can't get a list of roles." , ex);
        }
        return roles;
    }

    @Override
    public Role add(Role role) {
        try (Connection con = dataSource.getConnection()) {
            insertRole(role, con);
        } catch (SQLException ex) {
            throw new RepositoryException("Can't add a role." , ex);
        }
        return role;
    }

    @Override
    public void addAll(List<Role> roles) {
        try (Connection con = dataSource.getConnection()) {
            con.setAutoCommit(false);
            try {
                for (Role role : roles) {
                    insertRole(role, con);
                }
                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                throw new RepositoryException("Can't add a list of roles." , ex);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            throw new RepositoryException("Can't add a list of roles." , ex);
        }
    }

    @Override
    public void delete(Integer roleId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.DELETE_ROLE_QUERY)) {
            preparedStatement.setInt(1, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException("Can't delete a role." , ex);
        }
    }

    @Override
    public void update(Role role) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.UPDATE_ROLE_QUERY)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setInt(2, role.getRoleId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RepositoryException("Can't update a role." , ex);
        }
    }

    @Override
    public Role selectById(Integer roleId) {
        Role role = new Role();
        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(QueryConstants.SELECT_ROLE_BY_ID_QUERY)) {
            preparedStatement.setInt(1, roleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = getRole(resultSet);
            }
        } catch (SQLException ex) {
            throw new RepositoryException("Can't get a role by id." , ex);
        }
        return role;
    }

    private void insertRole(Role role, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(QueryConstants.INSERT_ROLE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getRoleName());

            final int effectiveRows = preparedStatement.executeUpdate();

            if (effectiveRows == 1) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        role.setRoleId(generatedKeys.getInt(QueryConstants.ROLE_ID_COLUMN));
                    }
                }
            }
        }
    }

    private Role getRole(ResultSet resultSet) {
        Role role = new Role();
        try {
            role.setRoleId(resultSet.getInt(QueryConstants.ROLE_ID_COLUMN));
            role.setRoleName(resultSet.getString(QueryConstants.ROLE_NAME_COLUMN));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
