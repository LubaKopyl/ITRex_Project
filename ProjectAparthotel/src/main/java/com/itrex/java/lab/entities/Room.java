package com.itrex.java.lab.entities;

import javax.persistence.*;

@Entity
@Table(name = "rooms", schema = "public")

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;
    @Column(name = "number_of_guests")
    private Integer numberOfGuests;
    @Column(name = "number_of_beds")
    private Integer numberOfBeds;
    @Column(name = "has_kitchen")
    private Boolean hasKitchen;
    @Column(name = "has_tv")
    private Boolean hasTV;
    @Column(name = "has_washer")
    private Boolean hasWasher;
    @Column(name = "has_hair_dryer")
    private Boolean hasHairDryer;
    @Column(name = "has_air_conditioning")
    private Boolean hasAirConditioning;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private Booking booking;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private Price price;

    public Room() {

    }

    public Room(Integer roomId, Integer numberOfGuests, Integer numberOfBeds, Boolean hasKitchen,
                Boolean hasTV, Boolean hasWasher, Boolean hasHairDryer, Boolean hasAirConditioning) {
        this.roomId = roomId;
        this.numberOfGuests = numberOfGuests;
        this.numberOfBeds = numberOfBeds;
        this.hasKitchen = hasKitchen;
        this.hasTV = hasTV;
        this.hasWasher = hasWasher;
        this.hasHairDryer = hasHairDryer;
        this.hasAirConditioning = hasAirConditioning;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Boolean getHasKitchen() {
        return hasKitchen;
    }

    public void setHasKitchen(Boolean hasKitchen) {
        this.hasKitchen = hasKitchen;
    }

    public Boolean getHasTV() {
        return hasTV;
    }

    public void setHasTV(Boolean hasTV) {
        this.hasTV = hasTV;
    }

    public Boolean getHasWasher() {
        return hasWasher;
    }

    public void setHasWasher(Boolean hasWasher) {
        this.hasWasher = hasWasher;
    }

    public Boolean getHasHairDryer() {
        return hasHairDryer;
    }

    public void setHasHairDryer(Boolean hasHairDryer) {
        this.hasHairDryer = hasHairDryer;
    }

    public Boolean getHasAirConditioning() {
        return hasAirConditioning;
    }

    public void setHasAirConditioning(Boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    @Override
    public String toString() {
        return "\nRoom{" +
                "roomId=" + roomId +
                ", numberOfGuests=" + numberOfGuests +
                ", numberOfBeds=" + numberOfBeds +
                ", hasKitchen=" + hasKitchen +
                ", hasTV=" + hasTV +
                ", hasWasher=" + hasWasher +
                ", hasHairDryer=" + hasHairDryer +
                ", hasAirConditioning=" + hasAirConditioning +
                '}';
    }
}
