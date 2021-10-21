package com.itrex.java.lab.repositories.impl;

import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.repositories.RoleRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCRoleRepositoryImpl implements RoleRepository {

    private static final String ROLE_ID_COLUMN = "role_Id";
    private static final String ROLE_NAME_COLUMN = "role_name";

    private static final String SELECT_ALL_QUERY = "SELECT * FROM roles";
    private static final String INSERT_ROLE_QUERY = "INSERT INTO roles(role_name) VALUES (?)";
    private static final String DELETE_ROLE_QUERY = "DELETE FROM roles WHERE role_id=?";
    private static final String UPDATE_ROLE_QUERY = "UPDATE roles SET role_name=? WHERE role_id=?";
    private static final String SELECT_ROLE_BY_ID_QUERY = "SELECT * FROM roles WHERE role_id=?";

    private final DataSource dataSource;

    public JDBCRoleRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Role> selectAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement stm = con.createStatement();
             ResultSet resultSet = stm.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                Role role = getRole(resultSet);
                roles.add(role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roles;
    }

    @Override
    public void add(Role role) {
        try (Connection con = dataSource.getConnection()) {
            insertRole(role, con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
    public void delete(Integer roleId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE_QUERY)) {
            preparedStatement.setInt(1, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE_QUERY)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setInt(2, role.getRoleId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Role selectById(Integer roleId) {
        Role role = new Role();
        try (Connection con = dataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SELECT_ROLE_BY_ID_QUERY)) {
            preparedStatement.setInt(1, roleId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = getRole(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return role;
    }

    private void insertRole(Role role, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_ROLE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getRoleName());

            final int effectiveRows = preparedStatement.executeUpdate();

            if (effectiveRows == 1) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        role.setRoleId(generatedKeys.getInt(ROLE_ID_COLUMN));
                    }
                }
            }
        }
    }

    private Role getRole(ResultSet resultSet) {
        Role role = new Role();
        try {
            role.setRoleId(resultSet.getInt(ROLE_ID_COLUMN));
            role.setRoleName(resultSet.getString(ROLE_NAME_COLUMN));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
