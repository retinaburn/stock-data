package com.rbc.stockdata.service

import com.rbc.stockdata.model.StockData
import com.rbc.stockdata.repository.StockRespository
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import spock.lang.Shared
import spock.lang.Specification

class BulkInsertTest extends Specification{
    @Shared
    BulkInsert insert
    @Shared
    StockRespository repo

    def setup(){
        repo = Mock(StockRespository) {
            save(_) >> { return new StockData() }
        }
        insert = new BulkInsert(repo)
    }

    def "test insertFile with header line"(){
        given:
        MockMultipartFile mockFile = new MockMultipartFile("name", "1234".getBytes())
        insert.insertFile("123", mockFile)

        expect:
        1==1
    }

    def "test insertFile with no data"(){
        given:
        MockMultipartFile mockFile = new MockMultipartFile("name", new byte[0])
        insert.insertFile("123", mockFile)

        expect:
        1==1
    }

    def "test with header and one row of data"(){
        when:
        MockMultipartFile mockFile = new MockMultipartFile("name", sampleDataHeaderWithOneRow.getBytes())
        insert.insertFile("123", mockFile)

        then:
        1 * repo.save(_) //save called once in insertFile
    }

    def "test with header and three row of data"(){
        when:
        MockMultipartFile mockFile = new MockMultipartFile("name", sampleDataHeaderWithThreeRows.getBytes())
        insert.insertFile("123", mockFile)

        then:
        3 * repo.save(_) //save called three times in insertFile
    }

    def "test with header and too few fields of data"(){
        when:
        MockMultipartFile mockFile = new MockMultipartFile("name", sampleDataHeaderWithOnePartialRow.getBytes())
        insert.insertFile("123", mockFile)

        then:
        0 * repo.save(_) //save called once in insertFile
    }

    String sampleDataHeaderWithOneRow =
            "quarter,stock,date,open,high,low,close,volume,percent_change_price,percent_change_volume_over_last_wk,previous_weeks_volume,next_weeks_open,next_weeks_close,percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend\n" +
                    "1,AA,1/7/2011,\$15.82,\$16.72,\$15.78,\$16.42,239655616,3.79267,,,\$16.71,\$15.97,-4.42849,26,0.182704\n"

    String sampleDataHeaderWithThreeRows =
            "quarter,stock,date,open,high,low,close,volume,percent_change_price,percent_change_volume_over_last_wk,previous_weeks_volume,next_weeks_open,next_weeks_close,percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend\n" +
                    "1,AA,1/7/2011,\$15.82,\$16.72,\$15.78,\$16.42,239655616,3.79267,,,\$16.71,\$15.97,-4.42849,26,0.182704\n" +
                    "1,AA,1/21/2011,\$16.19,\$16.38,\$15.60,\$15.79,138428495,-2.47066,-43.02495926,242963398,\$15.87,\$16.13,1.63831,12,0.189994\n" +
                    "1,AA,1/28/2011,\$15.87,\$16.63,\$15.82,\$16.13,151379173,1.63831,9.355500109,138428495,\$16.18,\$17.14,5.93325,5,0.185989\n"

    String sampleDataHeaderWithOnePartialRow =
                    "quarter,stock,date,open,high,low,close,volume,percent_change_price,percent_change_volume_over_last_wk,previous_weeks_volume,next_weeks_open,next_weeks_close,percent_change_next_weeks_price,days_to_next_dividend,percent_return_next_dividend\n" +
                            "1,AA,"

}
