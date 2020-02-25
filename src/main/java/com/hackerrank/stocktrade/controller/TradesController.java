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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {


    @Autowired
    private TradeService tradeService;

        @GetMapping("/stocks/{stockSymbol}/{tradeType}/{startDate}/{endDate}")
    public ResponseEntity<List<Trade>> getTradeByStockSymbolAndTradeType(@PathVariable String stockSymbol, @PathVariable String tradeType, @PathVariable Timestamp startDate, @PathVariable Timestamp endDate){


        return new ResponseEntity<>(tradeService.getTradeByStockSymbolAndTradeType(stockSymbol, tradeType, startDate, endDate), null, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Trade> addTrade(@Valid @RequestBody Trade trade){
        //        if(trade.getId() != null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        else{
//            System.out.println(trade);
//            return new ResponseEntity<>(tradeService.addTrade(trade), null, HttpStatus.OK);
//        }
        return new ResponseEntity<>(tradeService.addTrade(trade), null, HttpStatus.OK);


    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteTradeByUserId(@PathVariable Long userId){
        tradeService.deleteTrade(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id){
        return new ResponseEntity<>(tradeService.getTradeById(id), null, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Trade>> getAllTradesByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(tradeService.getAllTradesByUserId(userId), null, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Trade>> getAllTrades(){
        return new ResponseEntity<>(tradeService.getAllTrades(), null, HttpStatus.OK);
    }

    //    @DeleteMapping("/all")
//    public ResponseEntity<Void> deleteAllTrades(){
//        tradeService.deleteAllTrades();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    public Timestamp convertDateToImeStamp(String strDate){
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(strDate);
            Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }

}
