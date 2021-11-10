package com.itrex.java.lab.service.impl;

import com.itrex.java.lab.dto.BookingDTO;
import com.itrex.java.lab.entities.Booking;
import com.itrex.java.lab.repositories.BookingRepository;
import com.itrex.java.lab.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<BookingDTO> selectAll() {
        return bookingRepository.selectAll().stream()
                .map(BookingServiceImpl::convertBookingEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO add(BookingDTO bookingDTO) {
        return convertBookingEntityToDTO(bookingRepository.add(convertBookingDTOToEntity(bookingDTO)));
    }

    @Override
    public void addAll(List<BookingDTO> bookingsDTO) {
        bookingRepository.addAll(convertListDTOToBooking(bookingsDTO));
    }

    @Override
    public void delete(Integer bookingId) {
        bookingRepository.delete(bookingId);
    }

    @Override
    public void update(BookingDTO bookingDTO) {
        bookingRepository.update(convertBookingDTOToEntity(bookingDTO));
    }

    public static BookingDTO convertBookingEntityToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setRoom(RoomServiceImpl.convertRoomEntityToDTO(booking.getRoom()));
        bookingDTO.setUser(UserServiceImpl.convertUserEntityToDTO(booking.getUser()));
        bookingDTO.setArrivalDate(booking.getArrivalDate());
        bookingDTO.setDepartureDate(booking.getDepartureDate());
        bookingDTO.setTotalPrice(booking.getTotalPrice());
        return bookingDTO;
    }

    public static Booking convertBookingDTOToEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());
        booking.setRoom(RoomServiceImpl.convertRoomDTOToEntity(bookingDTO.getRoom()));
        booking.setUser(UserServiceImpl.convertUserDTOToEntity(bookingDTO.getUser()));
        booking.setArrivalDate(bookingDTO.getArrivalDate());
        booking.setDepartureDate(bookingDTO.getDepartureDate());
        booking.setTotalPrice(bookingDTO.getTotalPrice());
        return booking;
    }

    private List<Booking> convertListDTOToBooking(List<BookingDTO> bookingsDTO) {
        return bookingsDTO.stream()
                .map(BookingServiceImpl::convertBookingDTOToEntity)
                .collect(Collectors.toList());
    }
}
