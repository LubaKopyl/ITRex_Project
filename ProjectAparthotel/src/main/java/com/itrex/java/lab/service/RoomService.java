package com.itrex.java.lab.service;

import com.itrex.java.lab.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> selectAll();

    RoomDTO add(RoomDTO roomDTO);

    void addAll(List<RoomDTO> roomsDTO);

    void delete(Integer roomId);

    void update(RoomDTO roomDTO);
}
