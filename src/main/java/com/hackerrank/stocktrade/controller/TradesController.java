package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.interfaces.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {


    @Autowired
    private TradeService tradeService;

    public TradesController(TradeService tradeService1) {
        this.tradeService = tradeService1;
    }

    @GetMapping("/stocks/{stockSymbol}, {tradeType}, {startDate}, {endDate}")
    public ResponseEntity<List<Trade>> getTradeByStockSymbolAndTradeType(@PathVariable String symbol, @PathVariable String type, @PathVariable Timestamp startDate, @PathVariable Timestamp endDate){

        return new ResponseEntity<>(tradeService.getTradeByStockSymbolAndTradeType(symbol, type, startDate, endDate), null, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Trade> addTrade(@Valid @RequestBody Trade trade){

        if(trade.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        else{
            return new ResponseEntity<>(tradeService.addTrade(trade), null, HttpStatus.OK);
        }

    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllTrades(){
        tradeService.deleteAllTrades();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteTradeByUserId(@PathVariable Long id){
        tradeService.deleteTrade(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeByUserId(@PathVariable Long id){
        return new ResponseEntity<>(tradeService.getTrade(id), null, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Trade>> getTradeByUsserId(@PathVariable Long useriId){
        return new ResponseEntity<>(tradeService.getAllTradeByUserId(useriId), null, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Trade>> getAllTrades(){
        return new ResponseEntity<>(tradeService.getAllTrades(), null, HttpStatus.OK);
    }



    
}
