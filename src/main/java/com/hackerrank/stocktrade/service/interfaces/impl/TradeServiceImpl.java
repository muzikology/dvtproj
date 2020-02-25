package com.hackerrank.stocktrade.service.interfaces.impl;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.service.interfaces.TradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        System.out.println(tradeRepository.getOne(id));
        return tradeRepository.getOne(id);

    }

    @Transactional
    public List<Trade> getAllTrades(){

        return tradeRepository.findAll();
    }

    @Transactional
    public List<Trade> getAllTradesByUserId(Long id){

        System.out.println(tradeRepository.findAllByUserId(id));
        return tradeRepository.findAllByUserId(id);
    }

    @Transactional
    public List<Trade> getTradeByStockSymbolAndTradeType(String symbol, String type, Timestamp startDate, Timestamp endDate){

        return tradeRepository.findAllBySymbolAndType(symbol, type)
        .stream()
                .filter(x->x.getTimeStamp().before(endDate) && x.getTimeStamp().after(startDate))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Trade> getTradeByStockSymbolAndDateRange(String symbol, Float price, Timestamp startDate, Timestamp endDate){

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
