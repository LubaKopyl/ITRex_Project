package com.itrex.java.lab.repositories;

import com.itrex.java.lab.entities.Price;
import java.util.List;

public interface PriceRepository {
    List<Price> selectAll();

    Price add(Price price);

    void addAll(List<Price> prices);

    void delete(Integer priceId);

    void update(Price price);
}
