package com.rbc.stockdata.rest;

import com.rbc.stockdata.repository.StockRespository;
import com.rbc.stockdata.model.StockData;
import com.rbc.stockdata.service.BulkInsert;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
public class RestApi {
    private final StockRespository repo;
    private final BulkInsert insertService;

    @Autowired
    public RestApi(StockRespository repo,
                   BulkInsert insertService){
        this.repo = repo;
        this.insertService = insertService;
    }

    @PostMapping("bulk-insert")
    void bulkInsert(@RequestParam("file") MultipartFile file){
        log.debug("bulkInsert file: {}, size: {}", file.getName(), file.getSize());

        insertService.insertFile(file);

        for (StockData item : repo.findAll()){
            log.debug("Item: {}", item);
        }
    }

    @GetMapping("stock-data/{id}")
    List<StockData> getStock(@PathVariable String id){
        log.debug("getStock {}", id);
      return repo.findByStock(id);
    }

}
