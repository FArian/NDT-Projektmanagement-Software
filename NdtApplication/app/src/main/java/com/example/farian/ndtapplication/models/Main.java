package com.example.farian.ndtapplication.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Created by F.Arian on 06.11.17.
 */

public class Main {

	public static void main(String[] args) {

		Personal personal = new Personal("Farhad", "Arian", "14.02.1983", 1, "KolumbusStr20A", "28.10.2017",
				PersonalType.RADIOGRAPHER);
		System.out.println(personal);

	}

}
