package com.androidjson.firebasegooglelogin_androidjsoncom.models.model;

import android.support.v4.app.DialogFragment;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Period;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


/**
 * Created by F.Arian on 06.11.17.
 */
public abstract class DATA extends DialogFragment {

    private static final String DD_MM_YYYY = "dd.MM.yyyy";
    private static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final Date DATE = new Date();
    private static final DateTimeFormatter DATE_TIME_FORMATTER_GERMAN = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private static final long ltime = DATE.getTime() + 8 * 24 * 60 * 60 * 1000;


    public static String dateUpDate(String dateStr) {

        SimpleDateFormat format2 = new SimpleDateFormat(DD_MM_YYYY);
        Date date = null;
        try {
            date = format2.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return format2.format(date);

    }


    /**
     * get LocalDate with local format
     *
     * @return
     */
    public static String getLocalDate() {
        Calendar calendar = Calendar.getInstance();
        return DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
    }

    public static String convertDate(Calendar cal) {
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.mm.yyyy");
        return format1.format(cal.getTime());
    }

    /**
     * @param day ,moths ,year
     * @return new Date with adding new days or Months or years
     * If your don,t want to change a parameter please write 0
     */
    public static String changeDate(int day, int moths, int year) {
        Calendar calendar = Calendar.getInstance();
        if (day != 0) {
            calendar.add(Calendar.DAY_OF_MONTH, day);
            return DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
        }
        if (moths != 0) {
            calendar.add(Calendar.MONTH, moths);
            return DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
        }
        if (year != 0) {
            calendar.add(Calendar.YEAR, year);
            return DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
        }

        return null;
    }

    /**
     * get Calender with english format
     *
     * @return
     */
    public static String getCalendar() {
        return SIMPLE_DATE_FORMAT.format(CALENDAR.getTime()) + "\n";
    }

    /**
     * get Date
     *
     * @return
     */
    public static String getDate() {
        return SIMPLE_DATE_FORMAT.format(DATE);
    }

    /**
     * @param days
     * @return new Date with adding new days
     * If you want to run back date then enter your negative number
     */
    public static String addNewDayToDate(int days) {
        CALENDAR.setTime(new Date());
        CALENDAR.add(Calendar.DATE, days);
        return SIMPLE_DATE_FORMAT.format(CALENDAR.getTime());

    }

    /**
     * minus two date
     *
     * @param pastDate:LocalDateTime
     * @return result[0] min,s , result[1] hours,result[2] days ,result[3] months,result[4] years
     */
    public static double[] getPeriodTime(LocalDateTime pastDate) {
        double[] result = new double[5];
        LocalDateTime toDay = LocalDateTime.now();
        Period periodTime = Period.between(pastDate.toLocalDate(), toDay.toLocalDate());

        result[0] = pastDate.getMinute() - toDay.getMinute();
        result[1] = pastDate.getHour() - toDay.getHour();
        result[2] = periodTime.getDays();
        result[3] = periodTime.getMonths();
        result[4] = periodTime.getYears();

        return result;
    }

    /**
     * date just for String not accept
     *
     * @param strDate :System.in()
     * @return result[0] min,s , result[1] hours,result[2] days ,result[3] months,result[4] years
     */
    public static int[] getPeriodTime(String strDate) {
        int[] result = new int[5];
        LocalDateTime bd = LocalDateTime.parse(setTimetoDate(strDate), DATE_TIME_FORMATTER_GERMAN);
        //System.out.println("Date + Time"+setTimetoDate(strDate));
        LocalDateTime cd = LocalDateTime.now();
        int hr = cd.getHour() - bd.getHour();
        int mn = cd.getMinute() - bd.getMinute();
        Period time = Period.between(bd.toLocalDate(), cd.toLocalDate());
        result[0] = mn;
        result[1] = hr;
        result[2] = time.getDays();
        result[3] = time.getMonths();
        result[4] = time.getYears();
        return result;

    }

    /**
     * @param strDate date
     * @return dd.MM.yyyy HH:mm
     */
    public static String setTimetoDate(String strDate) {
        String date = strDate;
        String time = " 00:00";
        StringBuffer sbResult = new StringBuffer("");

        sbResult.setLength(strDate.length() + time.length());
        for (int i = 0; i < date.length() + time.length(); i++) {
            sbResult.replace(0, date.length(), date);
        }
        for (int i = date.length(); i < sbResult.length(); i++) {
            sbResult.replace(date.length(), sbResult.length(), time);
        }
        return sbResult.toString();
    }


    public static String creatId(String keyId) {
        String result = "";
        double d;
        for (int i = 1; i < 4; i++) {
            d = Math.random() * 10;
            result = result + ((int) d);
            if (i % 3 == 0) {
                result = result + keyId;
            }
        }
        return result;
    }

    public static String generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return String.valueOf(Integer.parseInt(str));
    }

    public static int generateUniqueId_Int() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    public static int counter(int x1) {
        int x = x1;
        x++;
        return x;
    }

    public static int counter() {
        int x = 0;
        x++;
        return x;
    }

    private static void showRandomInteger(int aStart, int aEnd, Random aRandom) {
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        int randomNumber = (int) (fraction + aStart);


    }

    /**
     *
     */
    public void goToCalender() {
        CALENDAR.set(Calendar.DAY_OF_MONTH, 1);
        CALENDAR.set(Calendar.MONTH, 1);
        CALENDAR.set(Calendar.YEAR, 2012);
        CALENDAR.add(Calendar.DAY_OF_MONTH, 5);
    }

    public String getDateFormat() {
        return DD_MM_YYYY;
    }


}

