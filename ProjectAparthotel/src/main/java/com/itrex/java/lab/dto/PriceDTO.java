package com.itrex.java.lab.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PriceDTO {
    private Integer priceId;
    private RoomDTO room;
    private Timestamp periodStart;
    private Timestamp periodEnd;
    private BigDecimal price;

    public PriceDTO() {

    }

    public PriceDTO(Integer priceId, RoomDTO room, Timestamp periodStart, Timestamp periodEnd, BigDecimal price) {
        this.priceId = priceId;
        this.room = room;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.price = price;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public Timestamp getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Timestamp periodStart) {
        this.periodStart = periodStart;
    }

    public Timestamp getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Timestamp periodEnd) {
        this.periodEnd = periodEnd;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PriceDTO{" +
                "priceId=" + priceId +
                ", room=" + room +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                ", price=" + price +
                '}';
    }
}
