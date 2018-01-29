package com.androidjson.firebasegooglelogin_androidjsoncom.models;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by F.Arian on 22.11.17.
 */

public class DateSettings implements DatePickerDialog.OnDateSetListener {
    private Context context;


    private int day,month,year;


    private String date;


    public DateSettings(Context context) {
        this.context = context;
    }


    /**
     * @param view       the picker associated with the dialog
     * @param year       the selected year
     * @param month      the selected month (0-11 for compatibility with
     *                   {@link Calendar#MONTH})
     * @param dayOfMonth th selected day of the month (1-31, depending on
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.setDay(dayOfMonth);
        this.setMonth(month);
        this.setYear(year);
        this.setDate(getDate()+"."+getMonth()+"."+getYear());

        Toast.makeText(context, "Selected date : " + getDay() + " . " + getMonth()+1 + " . " + getYear(),Toast.LENGTH_LONG).show();

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
