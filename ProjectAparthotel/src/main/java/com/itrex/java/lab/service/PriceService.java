package com.itrex.java.lab.service;

import com.itrex.java.lab.dto.PriceDTO;

import java.util.List;

public interface PriceService {
    List<PriceDTO> selectAll();

    PriceDTO add(PriceDTO priceDTO);

    void addAll(List<PriceDTO> pricesDTO);

    void delete(Integer priceId);

    void update(PriceDTO priceDTO);
}
