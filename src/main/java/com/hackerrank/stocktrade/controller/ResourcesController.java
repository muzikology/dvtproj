package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.interfaces.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

    @Autowired
    private TradeService tradeService;

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTrades(){
    tradeService.deleteAllTrades();
    return new ResponseEntity<>(HttpStatus.OK);
    }

}
