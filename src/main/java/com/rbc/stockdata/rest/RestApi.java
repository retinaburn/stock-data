package com.rbc.stockdata.rest;

import com.rbc.stockdata.model.StockData;
import com.rbc.stockdata.repository.StockRespository;
import com.rbc.stockdata.service.BulkInsert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/stock-data")
public class RestApi {
    private final StockRespository repo;
    private final BulkInsert insertService;

    @Autowired
    public RestApi(StockRespository repo,
                   BulkInsert insertService) {
        this.repo = repo;
        this.insertService = insertService;
    }

    @PostMapping("bulk-insert")
    void bulkInsert(@RequestParam("file") MultipartFile file) {
        log.debug("bulkInsert file: {}, size: {}", file.getName(), file.getSize());

        insertService.insertFile(file);

        for (StockData item : repo.findAll()) {
            log.debug("Item: {}", item);
        }
    }

    @GetMapping("{id}")
    List<StockData> getStock(@PathVariable String id) {
        log.debug("getStock {}", id);
        List<StockData> stocks = repo.findByStock(id);

        if (stocks.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find stock");
        return stocks;
    }

}
