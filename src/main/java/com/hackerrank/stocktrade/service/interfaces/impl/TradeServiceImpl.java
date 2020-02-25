package com.hackerrank.stocktrade.service.interfaces.impl;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


@Service
public class TradeServiceImpl {

    private  final TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository1) {
        this.tradeRepository = tradeRepository1;
    }


    public Trade addTrade(Trade trade){

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

    public Trade getTrade(Long id){

        return tradeRepository.getOne(id);

    }

    @Transactional
    public List<Trade> getAllTrades(){

        return tradeRepository.findAll();
    }

    @Transactional
    public List<Trade> getAllTradeByUserId(Long id){

        return tradeRepository.findAllByUserId(id);
    }

    @Transactional
    public List<Trade> getTradeByStockSymbolAndTradeType(String symbol, String type, Timestamp startDate, Timestamp endDate){

        return tradeRepository.findAllByStockSymbolAndTradeType(symbol, type, startDate, endDate);
    }

    public List<Trade> getTradeByStockSymbolAndDateRange(String symbol, Float price, Timestamp startDate, Timestamp endDate){

        return tradeRepository.findAllByStockSymbolAndDateRange(symbol, price, startDate, endDate);
    }

}
