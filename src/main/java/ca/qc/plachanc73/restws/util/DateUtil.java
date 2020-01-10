package ca.qc.plachanc73.restws.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static final String DATE_PATTERN = "yyyy-MM-dd";

	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

	public DateUtil() {
		// Aucune initialisation
	}

	/**
	 * Retourne la date formattée.
	 * 
	 * @param date
	 * @return Date
	 */
	public String formatDate(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * Retourne la date.
	 * 
	 * @param date
	 * @return Date
	 * @throws ParseException
	 */
	public Date formatDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}

	/**
	 * Ajoute au calendrier le nombre de jours d'affaires fournis.
	 * 
	 * @param cal
	 * @param numBusinessDays
	 * @return Calendar
	 */
	public static Calendar addBusinessDays(Calendar cal, int numBusinessDays) {
		int numNonBusinessDays = 0;

		for (int i = 0; i < numBusinessDays; i++) {
			cal.add(Calendar.DATE, 1);

			if (cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7) {
				numNonBusinessDays++;
			}
		}

		if (numNonBusinessDays > 0) {
			cal.add(Calendar.DATE, numNonBusinessDays);
		}

		return cal;
	}

	/**
	 * Ajoute à la date le nombre de jours fournis.
	 * 
	 * @param date
	 * @param numDays
	 * @return Date
	 */
	public static Date addDays(Date date, int numDays) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, numDays);
		return calendar.getTime();
	}

	/**
	 * Ajoute à la date le nombre de mois fournis.
	 * 
	 * @param date
	 * @param numMonths
	 * @return Date
	 */
	public static Date addMonths(Date date, int numMonths) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, numMonths);
		return calendar.getTime();
	}
}