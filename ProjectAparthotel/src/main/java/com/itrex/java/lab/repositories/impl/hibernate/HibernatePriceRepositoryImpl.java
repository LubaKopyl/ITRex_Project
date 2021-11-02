package com.itrex.java.lab.repositories.impl.hibernate;

import com.itrex.java.lab.entities.Price;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.PriceRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernatePriceRepositoryImpl implements PriceRepository {
    private final Session session;
    public HibernatePriceRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Price> selectAll() {
        return session.createQuery("from Price", Price.class).list();
    }

    @Override
    public Price add(Price price) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(price);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Can't add a price." , ex);
        }
        return price;
    }

    @Override
    public void addAll(List<Price> prices) {
        for (Price price : prices) {
            session.save(price);
        }
    }

    @Override
    public void delete(Integer priceId) {
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(session.load(Price.class, priceId));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Can't delete a booking." , ex);
        }
    }

    @Override
    public void update(Price price) {
        session.update(price);
    }
}
