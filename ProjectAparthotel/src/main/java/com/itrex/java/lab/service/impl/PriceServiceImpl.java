package com.itrex.java.lab.service.impl;

import com.itrex.java.lab.dto.PriceDTO;
import com.itrex.java.lab.entities.Price;
import com.itrex.java.lab.repositories.PriceRepository;
import com.itrex.java.lab.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<PriceDTO> selectAll() {
        return priceRepository.selectAll().stream()
                .map(PriceServiceImpl::convertPriceEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PriceDTO add(PriceDTO priceDTO) {
        return convertPriceEntityToDTO(priceRepository.add(convertPriceDTOToEntity(priceDTO)));
    }

    @Override
    public void addAll(List<PriceDTO> pricesDTO) {
        priceRepository.addAll(convertListDTOToPrice(pricesDTO));
    }

    @Override
    public void delete(Integer priceId) {
        priceRepository.delete(priceId);
    }

    @Override
    public void update(PriceDTO priceDTO) {
        priceRepository.update(convertPriceDTOToEntity(priceDTO));
    }

    public static PriceDTO convertPriceEntityToDTO(Price price) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setPriceId(price.getPriceId());
        priceDTO.setRoom(RoomServiceImpl.convertRoomEntityToDTO(price.getRoom()));
        priceDTO.setPeriodStart(price.getPeriodStart());
        priceDTO.setPeriodEnd(price.getPeriodEnd());
        priceDTO.setPrice(price.getPrice());
        return priceDTO;
    }

    public static Price convertPriceDTOToEntity(PriceDTO priceDTO) {
        Price price = new Price();
        price.setPriceId(priceDTO.getPriceId());
        price.setRoom(RoomServiceImpl.convertRoomDTOToEntity(priceDTO.getRoom()));
        price.setPeriodStart(priceDTO.getPeriodStart());
        price.setPeriodEnd(priceDTO.getPeriodEnd());
        price.setPrice(priceDTO.getPrice());
        return price;
    }

    private List<Price> convertListDTOToPrice(List<PriceDTO> pricesDTO) {
        return pricesDTO.stream()
                .map(PriceServiceImpl::convertPriceDTOToEntity)
                .collect(Collectors.toList());
    }
}
