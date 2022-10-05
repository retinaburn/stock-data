package com.rbc.stockdata.rest;

import com.rbc.stockdata.model.StockData;
import com.rbc.stockdata.repository.StockRepository;
import com.rbc.stockdata.service.BulkInsert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.rbc.stockdata.constant.Constants.HEADER_CLIENTID;

@Slf4j
@RestController
@RequestMapping("api/stock-data")
public class RestApi {
    private final StockRepository repo;
    private final BulkInsert insertService;

    @Autowired
    public RestApi(StockRepository repo,
                   BulkInsert insertService) {
        this.repo = repo;
        this.insertService = insertService;
    }

    @PostMapping("bulk-insert")
    void bulkInsert(
            @RequestHeader(name = HEADER_CLIENTID) String clientId,
            @RequestParam("file") MultipartFile file) {
        log.debug("bulkInsert client: {}, file: {}, size: {}", clientId, file.getName(), file.getSize());

        insertService.insertFile(clientId, file);

        for (StockData item : repo.findAll()) {
            log.debug("Item: {}", item);
        }
    }

    @GetMapping("{id}")
    List<StockData> getStock(
            @RequestHeader(name = HEADER_CLIENTID) String clientId,
            @PathVariable String id) {
        log.debug("client: {}, getStock {}", clientId, id);
        List<StockData> stocks = repo.findByClientIdAndStock(clientId, id);

        if (stocks.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find stock");
        return stocks;
    }

    @PostMapping
    StockData uploadStock(
            @RequestHeader(name = HEADER_CLIENTID) String clientId,
            @RequestBody StockData data){
        log.trace("original data: {}", data);
        data.setClientId(clientId);
        log.debug("client: {}, body: {}", clientId, data);
        return repo.save(data);
    }


}
