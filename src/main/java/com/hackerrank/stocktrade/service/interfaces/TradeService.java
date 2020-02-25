package com.hackerrank.stocktrade.service.interfaces;

import com.hackerrank.stocktrade.model.Trade;

import java.sql.Timestamp;
import java.util.List;

public interface TradeService {

    Trade addTrade(Trade trade);
    void deleteTrade(Long id);
    void deleteAllTrades();
    List<Trade> getAllTrades();
    Trade getTradeById(Long id);
    List<Trade> getAllTradesByUserId(Long id);
    List<Trade> getTradeByStockSymbolAndTradeType(String symbol, String type, Timestamp startDate, Timestamp endDate);
    List<Trade> getTradeByStockSymbolAndDateRange(String symbol, Float price, Timestamp startDate, Timestamp endDate);

}
