package com.itrex.java.lab.repositories.impl.hibernate;

import com.itrex.java.lab.entities.Room;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.RoomRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateRoomRepositoryImpl implements RoomRepository {
    private final Session session;
    public HibernateRoomRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Room> selectAll() {
        return session.createQuery("from Room", Room.class).list();
    }

    @Override
    public Room add(Room room) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(room);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Can't add a room." , ex);
        }
        return room;
    }

    @Override
    public void addAll(List<Room> rooms) {
        for (Room room : rooms) {
            session.save(room);
        }
    }

    @Override
    public void delete(Integer roomId) {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.load(Room.class, roomId));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Can't delete a room." , ex);
        }
    }

    @Override
    public void update(Room room) {
        session.update(room);
    }
}
