package ca.qc.plachanc73.restws.common.json;

import javax.validation.constraints.NotNull;

import ca.qc.plachanc73.restws.common.domaine.TypeClasse;
import ca.qc.plachanc73.restws.common.validation.strategy.TypeClasseStrategy;
import uk.co.jemos.podam.common.PodamStrategyValue;

public class FilterClasseJson {

	@PodamStrategyValue(value = TypeClasseStrategy.class)
	@NotNull
	private TypeClasse typeClasse;

	public TypeClasse getTypeClasse() {
		return typeClasse;
	}

	public void setTypeClasse(TypeClasse typeClasse) {
		this.typeClasse = typeClasse;
	}
}