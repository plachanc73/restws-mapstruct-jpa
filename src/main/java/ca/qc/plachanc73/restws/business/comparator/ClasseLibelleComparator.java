package ca.qc.plachanc73.restws.business.comparator;

import java.util.Comparator;

import org.springframework.data.domain.Sort.Direction;

import ca.qc.plachanc73.restws.business.domain.Language;
import ca.qc.plachanc73.restws.data.entity.Classe;

public class ClasseLibelleComparator extends AbstractEntityComparator implements Comparator<Classe> {

	public ClasseLibelleComparator(Language language, Direction direction) {
		super(language, direction);
	}

	@Override
	public int compare(Classe c1, Classe c2) {
		if (direction == null || direction.equals(Direction.ASC)) {
			return collator.compare(c1.getLibelle(), c2.getLibelle());
		} else {
			return collator.compare(c2.getLibelle(), c1.getLibelle());
		}
	}
}