package main.java.com.explainjava.dots;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DailySalesDTO {
    private Date day;

    private int totalSales;

    public DailySalesDTO(Date day, int totalSales) {
	this.day = day;
	this.totalSales = totalSales;
    }

    public Date getDay() {
	return day;
    }

    public void setDay(Date day) {
	this.day = day;
    }

    public int getTotalSales() {
	return totalSales;
    }

    public void setTotalSales(int totalSales) {
	this.totalSales = totalSales;
    }

    public String getDayString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = dateFormat.format(day);
        return dateString;
    
}
