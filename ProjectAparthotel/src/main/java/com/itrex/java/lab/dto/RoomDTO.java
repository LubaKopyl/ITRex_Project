package com.itrex.java.lab.dto;

public class RoomDTO {
    private Integer roomId;
    private Integer numberOfGuests;
    private Integer numberOfBeds;
    private Boolean hasKitchen;
    private Boolean hasTV;
    private Boolean hasWasher;
    private Boolean hasHairDryer;
    private Boolean hasAirConditioning;

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
}
