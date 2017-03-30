package com.tikal.service.stock;

import com.tikal.dtos.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Pattern;

/**
 * Created by pniel abramovich
 */
@RefreshScope
@Service
public class StockProviderYahoo implements StockProvider {

    @Value("${yahoo.parseString}")
    String parseString;
    @Value("${yahoo.url}")
    private String uri;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getStockProviderUrlBySymbol(String symbol) {
        return String.format(uri, symbol);
    }

    @Override
    public Stock getStockBySymbol(String symbol) {
        String uri = getStockProviderUrlBySymbol(symbol);
        String stockCsv = restTemplate.getForObject(uri, String.class);
        String[] stockinfo = stockCsv.split(parseString);
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setPrice(handleDouble(stockinfo[0]));
        stock.setVolume(handleInt(stockinfo[1]));
        stock.setPe(handleDouble(stockinfo[2]));
        stock.setEps(handleDouble(stockinfo[3]));
        stock.setWeek52low(handleDouble(stockinfo[4]));
        stock.setWeek52high(handleDouble(stockinfo[5]));
        stock.setDaylow(handleDouble(stockinfo[6]));
        stock.setDayhigh(handleDouble(stockinfo[7]));
        stock.setMovingav50day(handleDouble(stockinfo[8]));
        stock.setMarketcap(handleDouble(stockinfo[9]));
        stock.setName(stockinfo[10].replace("\"", ""));
        stock.setCurrency(stockinfo[11].replace("\"", ""));
        stock.setShortRatio(handleDouble(stockinfo[12]));
        stock.setPreviousClose(handleDouble(stockinfo[13]));
        stock.setOpen(handleDouble(stockinfo[14]));
        stock.setExchange(stockinfo[15].replace("\"", "").replaceAll("[\n\r]", ""));
        return stock;
    }


    public double handleDouble(String x) {
        Double y;
        if (Pattern.matches("N/A", x)) {
            y = 0.00;
        } else {
            y = Double.parseDouble(x);
        }
        return y;
    }

    public int handleInt(String x) {
        int y;
        if (Pattern.matches("N/A", x)) {
            y = 0;
        } else {
            y = Integer.parseInt(x);
        }
        return y;
    }
}
