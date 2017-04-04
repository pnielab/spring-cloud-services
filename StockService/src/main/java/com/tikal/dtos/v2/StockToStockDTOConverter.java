package com.tikal.dtos.v2;

import com.tikal.model.Stock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pniel Abramovich
 */
@Service(value = "v2StockToStockDTOConverter")
public class StockToStockDTOConverter implements Converter<Stock, StockDTO> {

    @Override
    public StockDTO convert(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setSymbol(stock.getSymbol());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setVolume(stock.getVolume());
        stockDTO.setPe(stock.getPe());
        stockDTO.setEps(stock.getEps());
        stockDTO.setWeek52low(stock.getWeek52low());
        stockDTO.setWeek52high(stock.getWeek52high());
        stockDTO.setDaylow(stock.getDaylow());
        stockDTO.setDayhigh(stock.getDayhigh());
        stockDTO.setMovingav50day(stock.getMovingav50day());
        stockDTO.setMarketcap(stock.getMarketcap());
        stockDTO.setName(stock.getName());
        stockDTO.setCurrency(stock.getCurrency());
        stockDTO.setShortRatio(stock.getShortRatio());
        stockDTO.setPreviousClose(stock.getPreviousClose());
        stockDTO.setOpen(stock.getOpen());
        stockDTO.setExchange(stock.getExchange());
        Map<String, String> affiliates = new HashMap<String, String>();
        affiliates.put("someAffiliateLink", "http://google.com");
        stockDTO.setAffiliets(affiliates);
        return stockDTO;
    }
}
