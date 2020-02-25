package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.interfaces.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {

    @Autowired
    private TradeService tradeService;

    public StocksController(TradeService tradeService1) {
        this.tradeService = tradeService1;
    }


    @GetMapping("/{stockSymbol},{price},{startDate},{endDate}")
    public ResponseEntity<List<Trade>> getTradeByStockSymbolAndDateRange(@PathVariable String symbol, @PathVariable Float price, @PathVariable Timestamp startDate, @PathVariable Timestamp endDate){

        List<Trade> trades = tradeService.getTradeByStockSymbolAndDateRange(symbol, price, startDate, endDate);
        if(trades.size() == 0){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are no trades in the given date range");
        }
        return new ResponseEntity<>(trades, null, HttpStatus.OK);
    }

    public Timestamp convertDateToImeStamp(String strDate){
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(strDate);
            Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }


}
