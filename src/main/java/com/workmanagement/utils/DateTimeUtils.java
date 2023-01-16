package com.workmanagement.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateTimeUtils {

	public boolean isDateInCurrentWeek(Date date) {
		Calendar currentCalendar = Calendar.getInstance();
		int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
		int year = currentCalendar.get(Calendar.YEAR);
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(date);
		int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
		int targetYear = targetCalendar.get(Calendar.YEAR);
		return week == targetWeek && year == targetYear;
	}

	public boolean isDateInNextWeek(Date date) {
		Calendar currentCalendar = Calendar.getInstance();
		int week = currentCalendar.get(Calendar.WEEK_OF_YEAR) + 1;
		int year = currentCalendar.get(Calendar.YEAR);
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(date);
		int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
		int targetYear = targetCalendar.get(Calendar.YEAR);
		return week == targetWeek && year == targetYear;
	}

	public boolean isToday(Date date) {
		Calendar currentCalendar = Calendar.getInstance();
		int day = currentCalendar.get(Calendar.DAY_OF_MONTH);
		int month = currentCalendar.get(Calendar.MONTH);
		int year = currentCalendar.get(Calendar.YEAR);
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(date);
		int targetDay = targetCalendar.get(Calendar.DAY_OF_MONTH);
		int targetMonth = targetCalendar.get(Calendar.MONTH);
		int targetYear = targetCalendar.get(Calendar.YEAR);
		return day == targetDay && month == targetMonth && year == targetYear;
	}
}
