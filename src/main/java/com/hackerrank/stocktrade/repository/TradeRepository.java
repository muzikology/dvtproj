package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long>  {

    List<Trade> findAllByUserId(Long id);

    List<Trade> findAllByStockSymbolAndTradeType(String symbol, String type, Timestamp startDate, Timestamp endDate);

    List<Trade> findAllByStockSymbolAndDateRange(String symbol, Float price, Timestamp startDate, Timestamp endDate);
}
