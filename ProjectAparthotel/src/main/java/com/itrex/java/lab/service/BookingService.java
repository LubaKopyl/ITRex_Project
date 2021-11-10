package com.itrex.java.lab.service;

import com.itrex.java.lab.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> selectAll();

    BookingDTO add(BookingDTO bookingDTO);

    void addAll(List<BookingDTO> bookingsDTO);

    void delete(Integer bookingId);

    void update(BookingDTO bookingDTO);
}
