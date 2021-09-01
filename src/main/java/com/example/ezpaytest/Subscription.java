package com.example.ezpaytest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Subscription {

    public int price;
    public String type;
    public LocalDate startDate;
    public LocalDate endDate;
    public String dayOfWeekMonth;
    public ArrayList <LocalDate> invoiceDates = new ArrayList<LocalDate>();

    private static final int day = 1;
    private static final int week = 7;
    private static final int month = 31;

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //Constructor to initialize class with values
    public Subscription (String price, String type, String startDate, String endDate, String dayOfWeekMonth){

        this.price = Integer.valueOf(price);
        this.type = type;
        this.startDate = LocalDate.parse( startDate , dateFormat );
        this.endDate = LocalDate.parse( endDate , dateFormat );
        this.dayOfWeekMonth = dayOfWeekMonth;
        this.invoiceDates = getInvoiceDates(this.startDate, this.endDate, getNumberOfDays(type), type, dayOfWeekMonth);
    }

    //Get all invoice dates within range
    /*private ArrayList<LocalDate> getInvoiceDates (LocalDate startDate, LocalDate endDate, int numberOfDays){

        ArrayList <LocalDate> invoiceDates = new ArrayList<LocalDate>();
        for (LocalDate date = startDate; date.compareTo(endDate) <= 0; date=date.plusDays(numberOfDays)){
            invoiceDates.add(date);
        }
        return invoiceDates;
    }*/

    private ArrayList<LocalDate> getInvoiceDates (LocalDate startDate, LocalDate endDate, int numberOfDays, String type, String dayOfWeekMonth){

        ArrayList <LocalDate> invoiceDates = new ArrayList<LocalDate>();
        LocalDate tempDate = null;

        if (type.equalsIgnoreCase("DAILY")){
            for (LocalDate date = startDate; date.compareTo(endDate) <= 0; date=date.plusDays(numberOfDays)){
                invoiceDates.add(date);
            }
        }
        else if (type.equalsIgnoreCase("WEEKLY")){
            for (LocalDate date = startDate; date.compareTo(endDate) <= 0; date=date.plusDays(1)){
                if (date.getDayOfWeek().toString().equalsIgnoreCase(dayOfWeekMonth)){
                    tempDate = date;
                    break;
                }
            }
            for (LocalDate date = tempDate; date.compareTo(endDate) <= 0; date=date.plusDays(numberOfDays)){
                invoiceDates.add(date);
            }
        }
        else if (type.equalsIgnoreCase("MONTHLY")){
            int dayOfWeekMonthInt = Integer.valueOf(dayOfWeekMonth);
            //user input data
            for (LocalDate date = startDate; date.compareTo(endDate) <= 0; date=date.plusDays(numberOfDays)){

                if(date.lengthOfMonth() < dayOfWeekMonthInt){
                    invoiceDates.add(date.with(TemporalAdjusters.lastDayOfMonth()));
                }
                else{
                    invoiceDates.add(date.withDayOfMonth(dayOfWeekMonthInt));
                }
            }
        }

        return invoiceDates;
    }

    //Obtain default number of days based on type
    public static int getNumberOfDays (String type){
        int numberOfDays;
        switch (type){
            case "WEEKLY":
                numberOfDays = week;
                break;
            case "MONTHLY":
                numberOfDays = month;
                break;
            default:
                numberOfDays = day;
        }
        return numberOfDays;
    }
}
