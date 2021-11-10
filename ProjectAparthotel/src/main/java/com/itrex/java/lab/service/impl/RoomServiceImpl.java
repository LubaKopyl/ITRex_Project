package com.itrex.java.lab.service.impl;

import com.itrex.java.lab.dto.RoomDTO;
import com.itrex.java.lab.entities.Room;
import com.itrex.java.lab.repositories.RoomRepository;
import com.itrex.java.lab.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> selectAll() {
        return roomRepository.selectAll().stream()
                .map(RoomServiceImpl::convertRoomEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO add(RoomDTO roomDTO) {
        return convertRoomEntityToDTO(roomRepository.add(convertRoomDTOToEntity(roomDTO)));
    }

    @Override
    public void addAll(List<RoomDTO> roomsDTO) {
        roomRepository.addAll(convertListDTOToRoom(roomsDTO));
    }

    @Override
    public void delete(Integer roomId) {
        roomRepository.delete(roomId);
    }

    @Override
    public void update(RoomDTO roomDTO) {
        roomRepository.update(convertRoomDTOToEntity(roomDTO));
    }

    public static RoomDTO convertRoomEntityToDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setNumberOfGuests(room.getNumberOfGuests());
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        roomDTO.setHasKitchen(room.getHasKitchen());
        roomDTO.setHasTV(room.getHasTV());
        roomDTO.setHasWasher(room.getHasWasher());
        roomDTO.setHasHairDryer(room.getHasHairDryer());
        roomDTO.setHasAirConditioning(room.getHasAirConditioning());
        return roomDTO;
    }

    public static Room convertRoomDTOToEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setNumberOfGuests(roomDTO.getNumberOfGuests());
        room.setNumberOfBeds(roomDTO.getNumberOfBeds());
        room.setHasKitchen(roomDTO.getHasKitchen());
        room.setHasTV(roomDTO.getHasTV());
        room.setHasWasher(roomDTO.getHasWasher());
        room.setHasHairDryer(roomDTO.getHasHairDryer());
        room.setHasAirConditioning(roomDTO.getHasAirConditioning());
        return room;
    }

    private List<Room> convertListDTOToRoom(List<RoomDTO> roomsDTO) {
        return roomsDTO.stream()
                .map(RoomServiceImpl::convertRoomDTOToEntity)
                .collect(Collectors.toList());
    }
}
