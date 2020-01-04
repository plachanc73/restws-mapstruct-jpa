package ca.qc.plachanc73.restws.common.json;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import ca.qc.plachanc73.restws.common.domaine.TypeClasse;

public class ClasseJson {

	private Long id;

	@NotBlank
	private String code;

	@NotBlank
	private String libelle;

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