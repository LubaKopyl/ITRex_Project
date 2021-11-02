package com.itrex.java.lab.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "prices", schema = "public")

public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Integer priceId;
    @Column(name = "period_start")
    private Timestamp periodStart;
    @Column(name = "period_end")
    private Timestamp periodEnd;
    private BigDecimal price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public Price() {

    }

    public Price(Integer priceId, Timestamp periodStart, Timestamp periodEnd, BigDecimal price, Room room) {
        this.priceId = priceId;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.price = price;
        this.room = room;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "\nPrice{" +
                "priceId=" + priceId +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                ", price=" + price +
                ", room=" + room +
                '}';
    }
}
