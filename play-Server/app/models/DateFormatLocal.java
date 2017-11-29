package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

/**
 * Created by F.Arian on 06.11.17.
 */
public abstract class DateFormatLocal {
    private static final String DATEFORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final Calendar CALENDAR = Calendar.getInstance();
    private static final Date date=new Date();
    private static final LocalDate localDate = LocalDate.now();
    private static final LocalDateTime localDateTime = LocalDateTime.now();
    private static final  long ltime=date.getTime()+8*24*60*60*1000;

    /**
     *
     */
    public void goToCalender(){
        CALENDAR.set(Calendar.DAY_OF_MONTH, 1);
        CALENDAR.set(Calendar.MONTH, 1);
        CALENDAR.set(Calendar.YEAR, 2012);
        CALENDAR.add(Calendar.DAY_OF_MONTH, 5);
    }

    public String getDateFormat() {
        return DATEFORMAT;
    }

    public static String dateUpDate(String dateStr) {

        SimpleDateFormat format2 = new SimpleDateFormat(DATEFORMAT);
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
     * get localDateTime with english format
     *
     * @return
     */
    public static String getLocalDateTime() {
        return dtf.format(localDateTime);
    }

    /**
     * get LocalDate with German Format
     *
     * @return
     */
    public static String getLocalDate() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy").format(localDate);
    }

    /**
     * get Calender with english format
     *
     * @return
     */
    public static String getCalendar() {
        return sdf.format(CALENDAR.getTime());
    }

    /**
     * get Date
     * @return
     */
    public static String getDate(){
        return sdf.format(date);
    }

    /**
     *
     * @param days
     * @return new Date with adding new days
     * If you want to run back date then enter your negative number
     */
    public static String addNewDayToDate(int days){
        CALENDAR.setTime(new Date());
        CALENDAR.add(Calendar.DATE, days);
        return sdf.format(CALENDAR.getTime());

    }

    @Override
    public String toString() {
        return "DateFormatLocal [getDateFormat()=" + getDateFormat() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }


}
