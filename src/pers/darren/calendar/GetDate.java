package pers.darren.calendar;

import static java.util.Calendar.HOUR;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import java.util.Calendar;
import java.util.Date;

public class GetDate {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(HOUR, 0);
		calendar.set(MINUTE, 0);
		calendar.set(SECOND, 0);

		System.out.println(calendar.getTime());
	}
}