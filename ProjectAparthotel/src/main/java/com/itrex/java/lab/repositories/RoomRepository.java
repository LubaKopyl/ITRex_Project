package com.itrex.java.lab.repositories;

import com.itrex.java.lab.entities.Room;
import java.util.List;

public interface RoomRepository {
    List<Room> selectAll();

    void add(Room room);

    void addAll(List<Room> rooms);

    void delete(Integer roomId);

    void update(Room room);
}
