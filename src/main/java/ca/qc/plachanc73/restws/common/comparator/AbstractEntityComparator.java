package ca.qc.plachanc73.restws.common.comparator;

import java.text.Collator;
import java.util.Locale;

import org.springframework.data.domain.Sort.Direction;

import ca.qc.plachanc73.restws.common.domaine.Language;

public abstract class AbstractEntityComparator {

	protected Collator collator;

	protected Direction direction;

	public AbstractEntityComparator(Language language, Direction direction) {
		initCollator(language);

		this.direction = direction;
	}

	private void initCollator(Language language) {
		switch (language) {
		case EN:
			this.collator = Collator.getInstance(Locale.ENGLISH);
		case FR:
			this.collator = Collator.getInstance(Locale.FRENCH);
		default:
			this.collator = Collator.getInstance(Locale.ENGLISH);
		}

		// This strategy mean it'll ignore the accents
		this.collator.setStrength(Collator.NO_DECOMPOSITION);
	}
}