package com.rbc.stockdata.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class StockData {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private int quarter;
    private String stock;
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private int volume;
    private double percentChangePrice;
    private double percentChangeVolumeOverLastWeek;
    private double previousWeeksVolume;
    private double nextWeeksOpen;
    private double nextWeeksClose;
    private double percentChangeNextWeeksPrice;
    private double daysToNextDividend;
    private double percentReturnNextDividend;

}
