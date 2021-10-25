package com.itrex.java.lab.repositories.impl.hibernate;

import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.repositories.RoleRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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
    public void add(Role role) {
        session.save(role);
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
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
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
