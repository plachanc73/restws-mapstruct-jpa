package ca.qc.plachanc73.restws.web.json;

import javax.validation.constraints.NotNull;

import ca.qc.plachanc73.restws.business.domain.TypeClasse;
import ca.qc.plachanc73.restws.web.validation.strategy.TypeClasseStrategy;
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