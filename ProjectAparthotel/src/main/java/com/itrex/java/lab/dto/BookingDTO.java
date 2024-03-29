package com.itrex.java.lab.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BookingDTO {
    private Integer bookingId;
    private RoomDTO room;
    private UserDTO user;
    private Timestamp arrivalDate;
    private Timestamp departureDate;
    private BigDecimal totalPrice;

    public BookingDTO() {

    }

    public BookingDTO(Integer bookingId, RoomDTO room, UserDTO user, Timestamp arrivalDate, Timestamp departureDate, BigDecimal totalPrice) {
        this.bookingId = bookingId;
        this.room = room;
        this.user = user;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.totalPrice = totalPrice;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingId=" + bookingId +
                ", room=" + room +
                ", user=" + user +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
