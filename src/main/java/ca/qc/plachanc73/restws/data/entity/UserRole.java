package ca.qc.plachanc73.restws.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ca.qc.plachanc73.restws.business.domain.TypeRole;

@Entity
@Table(name = "USER_ROLE")
public class UserRole extends AbstractEntity {

	private static final long serialVersionUID = -1505759308729659325L;

	@Id
	@Column(name = "ID_USER_ROLE")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_USER", nullable = false)
	private User user;

	@Column(name = "TYPE_ROLE", nullable = false)
	@Enumerated(EnumType.STRING)
	private TypeRole typeRole;

	@Column(name = "DATE_DEBUT", nullable = false)
	private Date dateDebut;

	@Column(name = "DATE_FIN", nullable = true)
	private Date dateFin;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TypeRole getTypeRole() {
		return typeRole;
	}

	public void setTypeRole(TypeRole typeRole) {
		this.typeRole = typeRole;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isActive(Date dateVerif) {
		return dateVerif != null && dateVerif.after(this.getDateDebut())
				&& (this.getDateFin() == null || dateVerif.before(this.getDateFin()));
	}
}