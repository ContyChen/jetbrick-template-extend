package jetx.ext.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jetbrick.template.JetAnnotations.Methods;

/**
 * 对java.util.Date做的method
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.4
 */
@Methods
public final class DateTimeMethods {
	
	/**
	 * <p>format instance of date</p>
	 * 
	 * @param date the date, not null
	 * @param pattern  format pattern, not null
	 * @return formatted string
	 */
	public static String toString(Date date, String pattern) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		if (pattern == null) {
			throw new IllegalArgumentException("The pattern must not be null");
		}
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * <p>format instance of calendar</p>
	 * 
	 * @param calendar the date, not null
	 * @param pattern  format pattern, not null
	 * @return formatted string
	 */
	public static String toString(Calendar calendar, String pattern) {
		if (calendar == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return toString(calendar.getTime(), pattern);
	}
	
    /**
     * <p>Checks if two date objects are on the same day ignoring time.</p>
     *
     * <p>28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true.
     * 28 Mar 2002 13:45 and 12 Mar 2002 13:45 would return false.
     * </p>
     * 
     * @param date1  the first date, not altered, not null
     * @param date2  the second date, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either date is <code>null</code>
     */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

    /**
     * <p>Checks if two calendar objects are on the same day ignoring time.</p>
     *
     * <p>28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true.
     * 28 Mar 2002 13:45 and 12 Mar 2002 13:45 would return false.
     * </p>
     * 
     * @param cal1  the first calendar, not altered, not null
     * @param cal2  the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
				&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
					.get(Calendar.DAY_OF_YEAR) == cal2
				.get(Calendar.DAY_OF_YEAR));
	}
    
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if two date objects represent the same instant in time.</p>
     *
     * <p>This method compares the long millisecond time of the two objects.</p>
     * 
     * @param date1  the first date, not altered, not null
     * @param date2  the second date, not altered, not null
     * @return true if they represent the same millisecond instant
     * @throws IllegalArgumentException if either date is <code>null</code>
     */
	public static boolean isSameInstant(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return date1.getTime() == date2.getTime();
	}

    /**
     * <p>Checks if two calendar objects represent the same instant in time.</p>
     *
     * <p>This method compares the long millisecond time of the two objects.</p>
     * 
     * @param cal1  the first calendar, not altered, not null
     * @param cal2  the second calendar, not altered, not null
     * @return true if they represent the same millisecond instant
     * @throws IllegalArgumentException if either date is <code>null</code>
     */
	public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return cal1.getTime().getTime() == cal2.getTime().getTime();
	}
    
    //-----------------------------------------------------------------------
    /**
     * Adds a number of years to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Adds a number of months to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Adds a number of weeks to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Adds a number of days to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Adds a number of hours to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Adds a number of minutes to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
	public static Date addMinutes(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Adds a number of seconds to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
	public static Date addSeconds(Date date, int amount) {
		return add(date, Calendar.SECOND, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Adds a number of milliseconds to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
	public static Date addMilliseconds(Date date, int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Adds to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param calendarField  the calendar field to add to
     * @param amount  the amount to add, may be negative
     * @return the new {@code Date} with the amount added
     * @throws IllegalArgumentException if the date is null
     */
	private static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}
    
    //-----------------------------------------------------------------------
    /**
     * Sets the years field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     */
	@Deprecated
	public static Date setYears(Date date, int amount) {
		return set(date, Calendar.YEAR, amount);
	}
	
	public static Date resetYears(Date date, int amount) {
		return set(date, Calendar.YEAR, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Sets the months field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     */
	@Deprecated
	public static Date setMonths(Date date, int amount) {
		return set(date, Calendar.MONTH, amount - 1);
	}

	public static Date resetMonths(Date date, int amount) {
		return set(date, Calendar.MONTH, amount - 1);
	}

    //-----------------------------------------------------------------------
    /**
     * Sets the day of month field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     */
	public static Date setDays(Date date, int amount) {
		return set(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date resetDays(Date date, int amount) {
		return set(date, Calendar.DAY_OF_MONTH, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Sets the hours field to a date returning a new object.  Hours range 
     * from  0-23.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     */
	@Deprecated
	public static Date setHours2(Date date, int amount) {
		return set(date, Calendar.HOUR_OF_DAY, amount);
	}

	public static Date resetHours(Date date, int amount) {
		return set(date, Calendar.HOUR_OF_DAY, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Sets the minute field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     */
	@Deprecated
	public static Date setMinutes2(Date date, int amount) {
		return set(date, Calendar.MINUTE, amount);
	}

	public static Date resetMinutes(Date date, int amount) {
		return set(date, Calendar.MINUTE, amount);
	}
    
    //-----------------------------------------------------------------------
    /**
     * Sets the seconds field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     */
	@Deprecated
	public static Date setSeconds2(Date date, int amount) {
		return set(date, Calendar.SECOND, amount);
	}

	public static Date resetSeconds(Date date, int amount) {
		return set(date, Calendar.SECOND, amount);
	}

    //-----------------------------------------------------------------------
    /**
     * Sets the miliseconds field to a date returning a new object.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     */
	@Deprecated
	public static Date setMilliseconds(Date date, int amount) {
		return set(date, Calendar.MILLISECOND, amount);
	}

	public static Date resetMilliseconds(Date date, int amount) {
		return set(date, Calendar.MILLISECOND, amount);
	}
    
    //-----------------------------------------------------------------------
    /**
     * Sets the specified field to a date returning a new object.  
     * This does not use a lenient calendar.
     * The original {@code Date} is unchanged.
     *
     * @param date  the date, not null
     * @param calendarField  the {@code Calendar} field to set the amount to
     * @param amount the amount to set
     * @return a new {@code Date} set with the specified value
     * @throws IllegalArgumentException if the date is null
     */
	private static Date set(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		// getInstance() returns a new object, so this method is thread safe.
		Calendar c = Calendar.getInstance();
		c.setLenient(false);
		c.setTime(date);
		c.set(calendarField, amount);
		return c.getTime();
	}

}
