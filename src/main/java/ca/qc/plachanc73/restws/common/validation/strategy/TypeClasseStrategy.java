package ca.qc.plachanc73.restws.common.validation.strategy;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.qc.plachanc73.restws.common.domaine.TypeClasse;
import uk.co.jemos.podam.common.AttributeStrategy;

public class TypeClasseStrategy implements AttributeStrategy<String> {

	@SuppressWarnings("serial")
	List<String> typesClasses = new ArrayList<String>() {
		{
			add(TypeClasse.PRIVEE.name());
			add(TypeClasse.PUBLIC.name());
		}
	};

	@Override
	public String getValue(Class<?> arg0, List<Annotation> arg1) {
		int idx = new Random().nextInt(typesClasses.size());
		String random = typesClasses.get(idx);
		typesClasses.remove(idx);

		return random;
	}
}