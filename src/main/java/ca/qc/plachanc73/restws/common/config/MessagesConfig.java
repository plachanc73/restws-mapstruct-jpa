package ca.qc.plachanc73.restws.common.config;

import java.util.Locale;
import java.util.ResourceBundle;

import ca.qc.plachanc73.restws.common.domaine.Language;

public class MessagesConfig {

	private static ResourceBundle resourceBundleMessagesEnglish = ResourceBundle.getBundle("messages", Locale.ENGLISH,
			new UTF8Control());

	private static ResourceBundle resourceBundleMessagesFrench = ResourceBundle.getBundle("messages", Locale.FRENCH,
			new UTF8Control());

	private MessagesConfig() {
		// Aucune initialisation
	}

	public static String getMessage(String messageKey, Language language) {
		switch (language) {
		case FR:
			return resourceBundleMessagesFrench.getString(messageKey);
		default:
			return resourceBundleMessagesEnglish.getString(messageKey);
		}
	}
}