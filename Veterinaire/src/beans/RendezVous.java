package beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "rendez_vous")
public class RendezVous {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idRendezVous;
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	@Column(name = "titre_rendez_vous")
	private String titreRendezVous;
	@Column(name = "date_debut")
	private Date dateDebut;
	@Column(name = "duree")
	private int duree;
	@Column(name = "etat")
	private int etat;
	public Long getIdRendezVous() {
		return idRendezVous;
	}
	public void setIdRendezVous(Long idRendezVous) {
		this.idRendezVous = idRendezVous;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String gettitreRendezVous() {
		return titreRendezVous;
	}
	public void settitreRendezVous(String titreRendezVous) {
		this.titreRendezVous = titreRendezVous;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public RendezVous(User user, String titreRendezVous, Date dateDebut, int duree, int etat) {
		super();
		this.user = user;
		this.titreRendezVous = titreRendezVous;
		this.dateDebut = dateDebut;
		this.duree = duree;
		this.etat = etat;
	}
	public RendezVous() {
		super();
	}
}