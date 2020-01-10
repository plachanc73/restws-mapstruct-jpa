package ca.qc.plachanc73.restws.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

	private ExceptionUtil() {
		// Aucune initialisation
	}

	/**
	 * Méthode pour obtenir la stacktrace d'une exception sous forme d'une string.
	 * Utile pour afficher la stacktrace dans les logs avec log4j
	 * 
	 * @param cause
	 * @return
	 */
	public static String getStringFromStackTrace(Throwable cause) {
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		cause.printStackTrace(printWriter);
		printWriter.flush();

		return writer.toString();
	}
}