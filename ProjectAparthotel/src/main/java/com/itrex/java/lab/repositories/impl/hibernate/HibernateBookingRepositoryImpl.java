package com.itrex.java.lab.repositories.impl.hibernate;

import com.itrex.java.lab.entities.Booking;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.BookingRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateBookingRepositoryImpl implements BookingRepository {
    private final Session session;

    public HibernateBookingRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Booking> selectAll() {
        return session.createQuery("from Booking", Booking.class).list();
    }

    @Override
    public Booking add(Booking booking) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(booking);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Can't add a booking." , ex);
        }
        return booking;
    }

    @Override
    public void addAll(List<Booking> bookings) {
        for (Booking booking : bookings) {
            session.save(booking);
        }
    }

    @Override
    public void delete(Integer bookingId) {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.load(Booking.class, bookingId));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Can't delete a booking." , ex);
        }
    }

    @Override
    public void update(Booking booking) {
        session.update(booking);
    }
}
