package com.rbc.stockdata.service;

import com.rbc.stockdata.model.StockData;
import com.rbc.stockdata.repository.StockRespository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static com.rbc.stockdata.constant.Constants.FIELD_DELIMITER;

@Slf4j
@Service
public class BulkInsert {

    private final StockRespository repo;

    @Autowired
    public BulkInsert(StockRespository repo){
        this.repo = repo;
    }

    public void insertFile(MultipartFile file){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){

            //read header
            if(reader.ready()){
                String header = reader.readLine();
            }
            //read rest of body
            while(reader.ready()){
                String line = reader.readLine();
                log.debug("Read: {}", line);
                String[] fields = line.split(FIELD_DELIMITER);
                StockData element = new StockData(fields);
                StockData saved = repo.save(element);
                log.debug("Saved: {}", saved);
            }
        } catch (IOException e){
            log.error("Exception found parsing file for bulkInsert", e);
        }
    }
}
