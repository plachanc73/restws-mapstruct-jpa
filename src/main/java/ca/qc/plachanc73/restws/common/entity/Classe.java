package ca.qc.plachanc73.restws.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ca.qc.plachanc73.restws.common.domaine.TypeClasse;

@Entity
@Table(name = "CLASSE")
public class Classe extends AbstractEntity {

	private static final long serialVersionUID = -821240902400276250L;

	@Id
	@SequenceGenerator(name = "SEQUENCE", sequenceName = "CLASSE_SEQ")
	@GeneratedValue(generator = "SEQUENCE")
	@Column(name = "ID_CLASSE", unique = true)
	private Long id;

	@Column(name = "CODE", nullable = false, length = 5)
	private String code;

	@Column(name = "LIBELLE", nullable = false, length = 100)
	private String libelle;

	@Column(name = "TYPE_CLASSE", nullable = false, length = 6)
	@Enumerated(EnumType.STRING)
	private TypeClasse typeClasse;

	@Override
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
}