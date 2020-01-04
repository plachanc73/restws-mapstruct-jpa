package ca.qc.plachanc73.restws.common.domaine;

public enum TypeClasse {

	PRIVEE("Privée"),

	PUBLIC("Public");

	private final String libelle;

	private TypeClasse(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}
}