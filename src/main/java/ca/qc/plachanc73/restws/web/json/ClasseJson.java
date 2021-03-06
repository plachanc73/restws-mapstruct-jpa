package ca.qc.plachanc73.restws.web.json;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ca.qc.plachanc73.restws.business.domain.TypeClasse;
import ca.qc.plachanc73.restws.web.validation.strategy.TypeClasseStrategy;
import uk.co.jemos.podam.common.PodamStrategyValue;

public class ClasseJson {

	private Long id;

	@NotBlank
	@Size(max = 5)
	private String code;

	@NotBlank
	@Size(max = 100)
	private String libelle;

	@PodamStrategyValue(TypeClasseStrategy.class)
	@NotNull
	private TypeClasse typeClasse;

	private String libelleTypeClasse;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public TypeClasse getTypeClasse() {
		return typeClasse;
	}

	public void setTypeClasse(TypeClasse typeClasse) {
		this.typeClasse = typeClasse;
	}

	public String getLibelleTypeClasse() {
		return libelleTypeClasse;
	}

	public void setLibelleTypeClasse(String libelleTypeClasse) {
		this.libelleTypeClasse = libelleTypeClasse;
	}
}