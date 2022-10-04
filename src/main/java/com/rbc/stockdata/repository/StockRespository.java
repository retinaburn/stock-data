package com.rbc.stockdata.repository;

import com.rbc.stockdata.model.StockData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRespository extends CrudRepository<StockData, Long> {
    List<StockData> findByStock(String stockName);

}
