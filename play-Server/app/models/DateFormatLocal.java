package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateFormatLocal {
	private static String DATEFORMAT = "dd.MM.yyyy";

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
