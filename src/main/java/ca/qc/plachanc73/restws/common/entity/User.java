package ca.qc.plachanc73.restws.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ca.qc.plachanc73.restws.common.domaine.Language;
import ca.qc.plachanc73.restws.common.domaine.TypeRole;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

	private static final long serialVersionUID = -8793410834710370622L;

	public static final String PARAM_KEY_USER = "USER";

	@Id
	@Column(name = "ID_USER", unique = true)
	private Long id;

	@Column(name = "CODE", nullable = false, length = 8)
	private String code;

	@Column(name = "FIRSTNAME", nullable = false, length = 60)
	private String prenom;

	@Column(name = "LASTNAME", nullable = false, length = 60)
	private String nom;

	@Column(name = "EMAIL", nullable = false, length = 65)
	private String courriel;

	@Column(name = "LANGUE", nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private Language language;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", targetEntity = UserRole.class)
	private List<UserRole> roles;

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

	public String getNomComplet() {
		return prenom + " " + nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<TypeRole> getActiveRoles() {
		List<TypeRole> activeRoles = new ArrayList<>();

		if (this.roles != null && !this.roles.isEmpty()) {
			Date now = new Date();

			for (UserRole userRole : this.roles) {
				if (userRole.isActive(now)) {
					activeRoles.add(userRole.getTypeRole());
				}
			}
		}

		return activeRoles;
	}
}