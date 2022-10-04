package com.rbc.stockdata.rest;

import com.rbc.stockdata.repository.StockRespository;
import com.rbc.stockdata.model.StockData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Log4j2
public class RestApi {

    @Autowired
    StockRespository repo;

    @PostMapping("bulk-insert")
    void bulkInsert(@RequestParam("file") MultipartFile file){
        log.debug("Inside bulk-insert file: {}, size: {}", file.getName(), file.getSize());

        StockData stock = new StockData();
        stock.setStock("NAME");
        repo.save(stock);

        for (StockData item : repo.findAll()){
            log.debug("Item: {}", item);
        }
    }
}
