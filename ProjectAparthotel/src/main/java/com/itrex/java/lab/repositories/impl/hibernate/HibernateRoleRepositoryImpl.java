package com.itrex.java.lab.repositories.impl.hibernate;

import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.RoleRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateRoleRepositoryImpl implements RoleRepository {

    private final Session session;
    public HibernateRoleRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Role> selectAll() {
        return session.createQuery("from Role", Role.class).list();
    }

    @Override
    public Role add(Role role) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(role);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Can't add a role." , ex);
        }
        return role;
    }

    @Override
    public void addAll(List<Role> roles) {
        for (Role role : roles) {
            session.save(role);
        }
    }

    @Override
    public void delete(Integer roleId) {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.load(Role.class, roleId));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Can't delete a role." , ex);
        }
    }

    @Override
    public void update(Role role) {
        session.update(role);
    }

    @Override
    public Role selectById(Integer roleId) {
        return session.get(Role.class, roleId);
    }
}
