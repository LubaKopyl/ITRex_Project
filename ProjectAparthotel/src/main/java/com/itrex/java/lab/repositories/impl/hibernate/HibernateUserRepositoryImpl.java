package com.itrex.java.lab.repositories.impl.hibernate;

import com.itrex.java.lab.dto.UserInfo;
import com.itrex.java.lab.entities.User;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {
    private final Session session;

    private static final String SELECT_ALL_USERS_WITH_ROLES_QUERY = "SELECT User.firstName, User.lastName, Role.roleName FROM User u " +
            "INNER JOIN UserRole ur ON u.userId = ur.userId INNER JOIN Role r ON ur.roleId = r.roleId";
    private static final String SELECT_ALL_USERS_BY_ROLES_QUERY = "SELECT u FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId " +
            "INNER JOIN Role r ON ur.roleId = r.roleId WHERE role_name = :roleName";

    public HibernateUserRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<User> selectAll() {
        return session.createQuery("from User", User.class).list();
    }

    @Override
    public List<UserInfo> getUsersInfo() {
        return null;
    }

    @Override
    public List<User> selectAllUsersByRole(String roleName) {
        List<User> users = new ArrayList<>();
        try {
            users = session.createQuery(SELECT_ALL_USERS_BY_ROLES_QUERY, User.class)
                    .setParameter("roleName", roleName).list();
        } catch (Exception ex) {
            throw new RepositoryException("Can't get a list of users by role." , ex);
        }
        return users;
    }

    @Override
    public void add(User user) {
        session.save(user);
    }

    @Override
    public void addAll(List<User> users) {
        for (User user : users) {
            session.save(user);
        }
    }

    @Override
    public void delete(Integer userId) {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.load(User.class, userId));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Can't delete a user." , ex);
        }
    }

    @Override
    public void update(User user) {
        session.update(user);
    }

    @Override
    public User selectById(Integer userId) {
        return session.get(User.class, userId);
    }
}
