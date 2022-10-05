package com.rbc.stockdata.repository;

import com.rbc.stockdata.model.StockData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<StockData, Long> {
    List<StockData> findByClientIdAndStock(String clientId, String stockName);

}
