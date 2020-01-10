package ca.qc.plachanc73.restws.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public class StringUtil {

	private StringUtil() {
		// Aucune initialisation
	}

	/**
	 * Nettoie et décompose une phrase en une liste de mots.
	 * 
	 * @param phrase
	 * @return List<String>
	 */
	public static List<String> nettoieEtDecomposePhraseEnMots(String phrase) {
		List<String> mots = new ArrayList<>();

		if (phrase != null) {
			String phraseNettoyee = phrase.replace("*", "%").toLowerCase().trim();

			// Pas de phrase de recherche
			if (phraseNettoyee.indexOf("\"") == -1) {
				return decomposePhraseEnMots(phraseNettoyee);
			} else {
				// Recherche de phrases
				int indexOfFirstQuote = phraseNettoyee.indexOf("\"");
				int indexOfSecondQuote = phraseNettoyee.indexOf("\"", phraseNettoyee.indexOf("\"") + 1);
				while (indexOfFirstQuote != -1 && indexOfSecondQuote != -1) {

					String mot = phraseNettoyee.substring(indexOfFirstQuote + 1, indexOfSecondQuote);
					phraseNettoyee = phraseNettoyee.replaceFirst("\"" + mot + "\"", "");

					mots.add(mot);
					indexOfFirstQuote = phraseNettoyee.indexOf("\"");
					indexOfSecondQuote = phraseNettoyee.indexOf("\"", phraseNettoyee.indexOf("\"") + 1);
				}
				mots.addAll(decomposePhraseEnMots(phraseNettoyee));
			}
		}

		return mots;
	}

	/**
	 * Décompose une phrase en une liste de mots.
	 * 
	 * @param phrase
	 * @return List<String>
	 */
	private static List<String> decomposePhraseEnMots(String phrase) {
		List<String> mots = new ArrayList<>();

		if (phrase != null) {
			String phraseNettoyee = phrase.trim();
			try (Scanner scanner = new Scanner(phraseNettoyee)) {
				scanner.useDelimiter(" ");// delimiteur par défaut
				while (scanner.hasNext()) {
					mots.add(scanner.next());
				}
			}
		}

		return mots;
	}

	/**
	 * Double les apostrophes pour que la valeur soit valide pour une requête
	 * SQL.
	 * 
	 * @param valeur
	 * @return String
	 */
	public static String doubleQuotes(String valeur) {
		return StringUtils.replace(valeur, "'", "''");
	}

	/**
	 * Sépare la valeur fournie en une liste de blocs de caractères selon le
	 * nombre de caractères fournis.
	 * 
	 * @param valeur
	 * @param nbCaracteres
	 * @return String[]
	 */
	public static String[] separerValeur(String valeur, int nbCaracteres) {
		String[] valeursSeparees;

		if (valeur.contains(System.lineSeparator())) {
			String valeurASeparer = StringUtils.replace(valeur, System.lineSeparator(), "CHR(13)");
			valeursSeparees = WordUtils.wrap(valeurASeparer, nbCaracteres).split(System.lineSeparator());

			for (int i = 0; i < valeursSeparees.length; i++) {
				valeursSeparees[i] = StringUtils.replace(valeursSeparees[i], "CHR(13)", "\n");
			}
		} else {
			valeursSeparees = WordUtils.wrap(valeur, nbCaracteres).split(System.lineSeparator());
		}

		return valeursSeparees;
	}
}