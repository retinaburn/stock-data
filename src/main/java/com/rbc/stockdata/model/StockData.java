package com.rbc.stockdata.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class StockData {

    public StockData(){
        //default constructor for Hibernate
    }
    public StockData(String clientId, String[] fields){
        this.clientId = clientId;
        quarter = Integer.parseInt(fields[0]);
        stock = fields[1];
        date = fields[2];
        open = fields[3];
        high = fields[4];
        low = fields[5];
        close = fields[6];
        volume = Integer.parseInt(fields[7]);
        percentChangePrice = fields[8];
        percentChangeVolumeOverLastWeek = fields[9];
        previousWeeksVolume = fields[10];
        nextWeeksOpen = fields[11];
        nextWeeksClose = fields[12];
        percentChangeNextWeeksPrice = fields[13];
        daysToNextDividend = fields[14];
        percentReturnNextDividend = fields[15];
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String clientId;
    private int quarter;
    private String stock;
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private int volume;

    //TODO Change following to nullable Doubles for if field empty
    private String percentChangePrice;
    private String percentChangeVolumeOverLastWeek;
    private String previousWeeksVolume;
    private String nextWeeksOpen;
    private String nextWeeksClose;
    private String percentChangeNextWeeksPrice;
    private String daysToNextDividend;
    private String percentReturnNextDividend;

}
