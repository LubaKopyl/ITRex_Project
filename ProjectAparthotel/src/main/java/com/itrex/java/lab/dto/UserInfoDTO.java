package com.itrex.java.lab.dto;

public class UserInfoDTO {

    private String firstName;
    private String lastName;
    private String roleName;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String firstName, String lastName, String roleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleName = roleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "\nUserInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
