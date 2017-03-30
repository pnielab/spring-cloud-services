package com.tikal.dtos;

import java.io.Serializable;

/**
 * Created by Pniel Abramovich
 */
public class StockDTO implements Serializable {


    private static final long serialVersionUID = 3480463627019607977L;

    private String symbol;
    private double price;
    private int volume;
    private double pe;
    private double eps;
    private double week52low;
    private double week52high;
    private double daylow;
    private double dayhigh;
    private double movingav50day;
    private double marketcap;
    private String name;
    private String currency;
    private double shortRatio;
    private double previousClose;
    private double open;
    private String exchange;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getWeek52low() {
        return week52low;
    }

    public void setWeek52low(double week52low) {
        this.week52low = week52low;
    }

    public double getWeek52high() {
        return week52high;
    }

    public void setWeek52high(double week52high) {
        this.week52high = week52high;
    }

    public double getDaylow() {
        return daylow;
    }

    public void setDaylow(double daylow) {
        this.daylow = daylow;
    }

    public double getDayhigh() {
        return dayhigh;
    }

    public void setDayhigh(double dayhigh) {
        this.dayhigh = dayhigh;
    }

    public double getMovingav50day() {
        return movingav50day;
    }

    public void setMovingav50day(double movingav50day) {
        this.movingav50day = movingav50day;
    }

    public double getMarketcap() {
        return marketcap;
    }

    public void setMarketcap(double marketcap) {
        this.marketcap = marketcap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getShortRatio() {
        return shortRatio;
    }

    public void setShortRatio(double shortRatio) {
        this.shortRatio = shortRatio;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
