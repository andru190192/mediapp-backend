package com.andresguachisaca.util;

import java.time.LocalDateTime;

public class Utils {

	public static final int NUMBER_OF_DAILY_APPOINTMENTS_ALLOWED = 2;
	
	public static final int DEFAULT_MORNING_START_TIME = 9;
	public static final int DEFAULT_MORNING_END_TIME = 12;
	public static final int DEFAULT_AFTERNOON_START_TIME = 16;
	public static final int DEFAULT_AFTERNOON_END_TIME = 18;
	
	


	public static LocalDateTime getDateWithEndTimeOfDay(LocalDateTime date) {
		date = date.withHour(23);
		date = date.withMinute(59);
		date = date.withSecond(59);
		return date;
	}

	public static LocalDateTime getDateWithStartTimeOfDay(LocalDateTime date) {
		date = date.withHour(00);
		date = date.withMinute(00);
		date = date.withSecond(00);
		return date;
	}

}
