package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by F.Arian on 06.11.17.
 */
public abstract class DateFormatLocal {
	private static String DATEFORMAT = "dd.MM.yyyy";
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private LocalDate localDate = LocalDate.now();

	public String getDateFormat() {
		return DATEFORMAT;
	}

	@Override
	public String toString() {
		return "DateFormatLocal [getDateFormat()=" + getDateFormat() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public static String dateUpDate(String ddmmyyy) {
		// SimpleDateFormat format2 = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat format2 = new SimpleDateFormat(DATEFORMAT);
		Date date = null;
		try {
			date = format2.parse(ddmmyyy);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return format2.format(date);

	}


}
