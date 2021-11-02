package com.itrex.java.lab.repositories;

import com.itrex.java.lab.entities.Booking;
import java.util.List;

public interface BookingRepository {
    List<Booking> selectAll();

    Booking add(Booking booking);

    void addAll(List<Booking> bookings);

    void delete(Integer bookingId);

    void update(Booking booking);
}
