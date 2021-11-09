package com.itrex.java.lab.repository.impl;

import com.itrex.java.lab.config.ApplicationContextConfiguration;
import com.itrex.java.lab.entities.Booking;
import com.itrex.java.lab.entities.Room;
import com.itrex.java.lab.entities.User;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.BookingRepository;
import com.itrex.java.lab.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateBookingRepositoryImplTest extends BaseRepositoryTest {

    private final ApplicationContext applicationContext;
    private final BookingRepository bookingRepository;

    public HibernateBookingRepositoryImplTest() {
        super();
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        bookingRepository = applicationContext.getBean(BookingRepository.class);
    }

    @Test
    public void selectAll_validData_shouldReturnTheNumberOfAllExistingBookings() throws RepositoryException {
        //given
        int expectedNumberOfBookings = 2;
        //when
        int actualNumberOfBookings = bookingRepository.selectAll().size();
        //then
        assertEquals(expectedNumberOfBookings, actualNumberOfBookings);
    }

    @Test
    void add_validDate_shouldReturnNewBooking() throws RepositoryException {
        //given
        Booking expectedBooking = new Booking(3, Timestamp.valueOf("2022-03-05 00:00:00"), Timestamp.valueOf("2022-03-09 00:00:00"), BigDecimal.valueOf(200),
                new Room(3, 4, 2, false, true, true, true, false),
                new User(3, "Maksim", "Morozov", "maksim@gmail.com", "12345maksim"));
        //when
        Booking actualBooking = bookingRepository.add(expectedBooking);
        //then
        assertBookingEquals(expectedBooking, actualBooking);
    }

    @Test
    void addAll_validDate_shouldReturnTheNumberOfBookings() throws RepositoryException {
        //given
        List<Booking> bookings = new ArrayList<>();
        Booking booking1 = new Booking(3, Timestamp.valueOf("2022-03-05 00:00:00"), Timestamp.valueOf("2022-03-09 00:00:00"), BigDecimal.valueOf(200),
                new Room(3, 4, 2, false, true, true, true, false),
                new User(3, "Maksim", "Morozov", "maksim@gmail.com", "12345maksim"));
        bookings.add(booking1);
        Booking booking2 = new Booking(4, Timestamp.valueOf("2022-03-10 00:00:00"), Timestamp.valueOf("2022-03-20 00:00:00"), BigDecimal.valueOf(500),
                new Room(2, 2, 2, true, true, true, true, true),
                new User(2, "Masha", "Petrova", "masha@gmail.com", "12345masha"));
        bookings.add(booking2);

        int expectedBookingsAmount = 4;
        //when
        bookingRepository.addAll(bookings);
        List<Booking> actualBookingsAmount = bookingRepository.selectAll();
        //then
        assertEquals(expectedBookingsAmount, actualBookingsAmount.size());
    }

    @Test
    void update_validData_shouldReturnUpdatedBooking() {
        //given
        Booking booking = new Booking(3, Timestamp.valueOf("2022-03-05 00:00:00"), Timestamp.valueOf("2022-03-09 00:00:00"), BigDecimal.valueOf(200),
                new Room(4, 1, 1, true, true, false, true, false),
                new User(4, "Anna", "Ivanova", "anna@gmail.com", "12345anna"));
        booking.setTotalPrice(BigDecimal.valueOf(220));
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(220);
        // when
        bookingRepository.update(booking);
        BigDecimal actualTotalPrice = booking.getTotalPrice();
        //then
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    void delete_validData_shouldReturnNewBookingsAmount() {
        //given
        int expectedBookingsAmount = 1;
        // when
        bookingRepository.delete(1);
        List<Booking> actualBookingsAmount = bookingRepository.selectAll();
        //then
        assertEquals(expectedBookingsAmount, actualBookingsAmount.size());
    }

    private void assertBookingEquals(Booking expectedBooking, Booking actualBooking) {
        assertEquals(expectedBooking.getBookingId(), actualBooking.getBookingId());
        assertEquals(expectedBooking.getArrivalDate(), actualBooking.getArrivalDate());
        assertEquals(expectedBooking.getDepartureDate(), actualBooking.getDepartureDate());
        assertEquals(expectedBooking.getTotalPrice(), actualBooking.getTotalPrice());
        assertEquals(expectedBooking.getRoom(), actualBooking.getRoom());
        assertEquals(expectedBooking.getUser(), actualBooking.getUser());
    }
}
