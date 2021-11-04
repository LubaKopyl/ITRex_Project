package com.itrex.java.lab.repository.impl;

import com.itrex.java.lab.config.ApplicationContextConfiguration;
import com.itrex.java.lab.entities.Room;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.RoomRepository;
import com.itrex.java.lab.repository.BaseRepositoryTest;
import com.itrex.java.lab.service.FlywayService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateRoomRepositoryImplTest extends BaseRepositoryTest {

    private final ApplicationContext applicationContext;
    private final FlywayService flywayService;
    private final RoomRepository roomRepository;

    public HibernateRoomRepositoryImplTest() {
        super();
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        flywayService = applicationContext.getBean(FlywayService.class);
        roomRepository = applicationContext.getBean(RoomRepository.class);
    }

    @Test
    public void selectAll_validData_shouldReturnTheNumberOfAllExistingRooms() throws RepositoryException {
        //given
        int expectedNumberOfRooms = 3;
        //when
        int actualNumberOfRooms = roomRepository.selectAll().size();
        //then
        assertEquals(expectedNumberOfRooms, actualNumberOfRooms);
    }

    @Test
    void add_validDate_shouldReturnNewUser() throws RepositoryException {
        //given
        Room expectedRoom = new Room(4, 1, 1, true, true, false, true, false);
        //when
        Room actualRoom = roomRepository.add(expectedRoom);
        //then
        assertRoomEquals(expectedRoom, actualRoom);
    }

    @Test
    void addAll_validDate_shouldReturnTheNumberOfRooms() throws RepositoryException {
        //given
        List<Room> rooms = new ArrayList<>();
        Room room1 = new Room(4, 1, 1, true, true, false, true, false);
        rooms.add(room1);
        Room room2 = new Room(5, 4, 3, true, true, true, true, false);
        rooms.add(room2);

        int expectedRoomsAmount = 5;
        //when
        roomRepository.addAll(rooms);
        List<Room> actualRoomsAmount = roomRepository.selectAll();
        //then
        assertEquals(expectedRoomsAmount, actualRoomsAmount.size());
    }

    @Test
    void update_validData_shouldReturnUpdatedRoom() {
        //given
        Room room = new Room(4, 1, 1, true, true, false, true, false);
        room.setHasWasher(true);
        Boolean expectedHasWasher = true;
        // when
        roomRepository.update(room);
        Boolean actualHasWasher = room.getHasWasher();
        //then
        assertEquals(expectedHasWasher, actualHasWasher);
    }

    @Test
    void delete_validData_shouldReturnNewRoomsAmount() {
        //given
        int expectedRoomsAmount = 2;
        // when
        roomRepository.delete(1);
        List<Room> actualRoomsAmount = roomRepository.selectAll();
        //then
        assertEquals(expectedRoomsAmount, actualRoomsAmount.size());
    }

    private void assertRoomEquals(Room expectedRoom, Room actualRoom) {
        assertEquals(expectedRoom.getRoomId(), actualRoom.getRoomId());
        assertEquals(expectedRoom.getNumberOfGuests(), actualRoom.getNumberOfGuests());
        assertEquals(expectedRoom.getNumberOfBeds(), actualRoom.getNumberOfBeds());
        assertEquals(expectedRoom.getHasKitchen(), actualRoom.getHasKitchen());
        assertEquals(expectedRoom.getHasTV(), actualRoom.getHasTV());
        assertEquals(expectedRoom.getHasWasher(), actualRoom.getHasWasher());
        assertEquals(expectedRoom.getHasHairDryer(), actualRoom.getHasHairDryer());
        assertEquals(expectedRoom.getHasAirConditioning(), actualRoom.getHasAirConditioning());
    }
}
