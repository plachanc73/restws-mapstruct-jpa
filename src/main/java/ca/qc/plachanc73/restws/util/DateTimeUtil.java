package ca.qc.plachanc73.restws.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private SimpleDateFormat dateTimeformat = new SimpleDateFormat(DATE_TIME_PATTERN);

	public DateTimeUtil() {
		// Aucune initialisation
	}

	/**
	 * Retourne la date et l'heure formattée.
	 * 
	 * @param date
	 * @return Date
	 */
	public String formatDateTime(Date date) {
		return dateTimeformat.format(date);
	}
}