package com.hackerrank.stocktrade.service.interfaces.impl;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.service.interfaces.TradeService;
import com.hazelcast.spi.exception.ResponseAlreadySentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TradeServiceImpl implements TradeService {

    private  final TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository1) {
        this.tradeRepository = tradeRepository1;
    }


    @Transactional
    public Trade addTrade(Trade trade){

        boolean checkId = tradeRepository.findAll()
                .stream()
                .anyMatch(x -> x.getId().equals(trade.getId()));
        if(checkId){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID for this trade already exists");
        }
        if(trade.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please use ID for this trade request");
        }
     return tradeRepository.save(trade);

    }

    @Transactional
    public void deleteTrade(Long id){

        tradeRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllTrades(){

         tradeRepository.deleteAll();
    }

    public Trade getTradeById(Long id){

        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please specify an ID for this trade request");
        }
        return tradeRepository.getOne(id);
    }

    @Transactional
    public List<Trade> getAllTrades(){

        return tradeRepository.findAll();
    }

    @Transactional
    public List<Trade> getAllTradesByUserId(Long id){

        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please specify a USER ID for this trade request");
        }
        return tradeRepository.findAllByUserId(id);
    }

    @Transactional
    public List<Trade> getTradeByStockSymbolAndTradeType(String symbol, String type, Timestamp startDate, Timestamp endDate){

        if(symbol == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade Symbol is Required for this Request");
        }

        if(type == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade Type is Required for this Request");
        }
        if(startDate == null || endDate == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start Date and End Date are Required for this Request");
        }
        return tradeRepository.findAllBySymbolAndType(symbol, type)
        .stream()
                .filter(x->x.getTimeStamp().before(endDate) && x.getTimeStamp().after(startDate))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Trade> getTradeByStockSymbolAndDateRange(String symbol, Float price, Timestamp startDate, Timestamp endDate){

        if(symbol == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade Symbol is Required for this Request");
        }

        if(price == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trade Price is Required for this Request");
        }
        if(startDate == null || endDate == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start Date and End Date are Required for this Request");
        }
        return tradeRepository.findAllBySymbolAndPrice(symbol, price)
                .stream()
                .filter(x->x.getTimeStamp().before(endDate) && x.getTimeStamp().after(startDate))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Trade> getTradeByMaxPrice(String symbol, Float price, Timestamp startDate, Timestamp endDate){

        return tradeRepository.findAllBySymbolAndPrice(symbol, price)
                .stream()
                .filter(x->x.getTimeStamp().before(endDate) && x.getTimeStamp().after(startDate))
                .collect(Collectors.toList());
    }

}
