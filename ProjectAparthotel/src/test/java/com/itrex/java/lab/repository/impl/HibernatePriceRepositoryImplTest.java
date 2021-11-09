package com.itrex.java.lab.repository.impl;

import com.itrex.java.lab.config.ApplicationContextConfiguration;
import com.itrex.java.lab.entities.Price;
import com.itrex.java.lab.entities.Room;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.PriceRepository;
import com.itrex.java.lab.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernatePriceRepositoryImplTest extends BaseRepositoryTest {

    private final ApplicationContext applicationContext;
    private final PriceRepository priceRepository;

    public HibernatePriceRepositoryImplTest() {
        super();
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        priceRepository = applicationContext.getBean(PriceRepository.class);
    }

    @Test
    public void selectAll_validData_shouldReturnTheNumberOfAllExistingPrices() throws RepositoryException {
        //given
        int expectedNumberOfPrices = 3;
        //when
        int actualNumberOfPrices = priceRepository.selectAll().size();
        //then
        assertEquals(expectedNumberOfPrices, actualNumberOfPrices);
    }

    @Test
    void add_validDate_shouldReturnNewPrice() throws RepositoryException {
        //given
        Price expectedPrice = new Price(4, Timestamp.valueOf("2022-03-01 00:00:01"), Timestamp.valueOf("2022-05-01 00:00:01"), BigDecimal.valueOf(50),
                new Room(3, 4, 2, false, true, true, true, false));
        //when
        Price actualPrice = priceRepository.add(expectedPrice);
        //then
        assertPriceEquals(expectedPrice, actualPrice);
    }

    @Test
    void addAll_validDate_shouldReturnTheNumberOfPrices() throws RepositoryException {
        //given
        List<Price> prices = new ArrayList<>();
        Price price1 = new Price(4, Timestamp.valueOf("2022-03-01 00:00:01"), Timestamp.valueOf("2022-05-01 00:00:01"), BigDecimal.valueOf(50),
                new Room(3, 4, 2, false, true, true, true, false));
        prices.add(price1);
        Price price2 = new Price(5, Timestamp.valueOf("2022-03-01 00:00:01"), Timestamp.valueOf("2022-05-01 00:00:01"), BigDecimal.valueOf(55),
                new Room(2, 2, 2, true, true, true, true, true));
        prices.add(price2);

        int expectedPricesAmount = 5;
        //when
        priceRepository.addAll(prices);
        List<Price> actualPricesAmount = priceRepository.selectAll();
        //then
        assertEquals(expectedPricesAmount, actualPricesAmount.size());
    }

    @Test
    void update_validData_shouldReturnUpdatedPrice() {
        //given
        Price price = new Price(4, Timestamp.valueOf("2022-03-01 00:00:01"), Timestamp.valueOf("2022-05-01 00:00:01"), BigDecimal.valueOf(50),
                new Room(3, 4, 2, false, true, true, true, false));
        price.setPrice(BigDecimal.valueOf(60));
        BigDecimal expectedPrice = BigDecimal.valueOf(60);
        // when
        priceRepository.update(price);
        BigDecimal actualPrice = price.getPrice();
        //then
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void delete_validData_shouldReturnNewPricesAmount() {
        //given
        int expectedPricesAmount = 2;
        // when
        priceRepository.delete(1);
        List<Price> actualPricesAmount = priceRepository.selectAll();
        //then
        assertEquals(expectedPricesAmount, actualPricesAmount.size());
    }

    private void assertPriceEquals(Price expectedPrice, Price actualPrice) {
        assertEquals(expectedPrice.getPriceId(), actualPrice.getPriceId());
        assertEquals(expectedPrice.getPeriodStart(), actualPrice.getPeriodStart());
        assertEquals(expectedPrice.getPeriodEnd(), actualPrice.getPeriodEnd());
        assertEquals(expectedPrice.getPrice(), actualPrice.getPrice());
        assertEquals(expectedPrice.getRoom(), actualPrice.getRoom());
    }
}
