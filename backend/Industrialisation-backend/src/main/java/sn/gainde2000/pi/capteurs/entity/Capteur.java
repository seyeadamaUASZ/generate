package sn.gainde2000.pi.capteurs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "td_capteur", schema = "")
public class Capteur implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCapteur;
	
	@Size(max = 255)
    @Column(name = "cap_libelle")
	private String libelle;
	@Size(max = 255)
    @Column(name = "cap_description")
	private String description;
	
	@OneToMany(mappedBy = "capteur")
	private Collection<DonneeRecupereeCapteur> donnees=new ArrayList<DonneeRecupereeCapteur>();
	
	
	@JsonIgnore
	public Collection<DonneeRecupereeCapteur> getDonnees() {
		return donnees;
	}
	public void setDonnees(Collection<DonneeRecupereeCapteur> donnees) {
		this.donnees = donnees;
	}
	public Long getIdCapteur() {
		return idCapteur;
	}
	public void setIdCapteur(Long idCapteur) {
		this.idCapteur = idCapteur;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Capteur(@Size(max = 255) String libelle, @Size(max = 255) String description) {
		super();
		this.libelle = libelle;
		this.description = description;
	}
	public Capteur() {
		super();
	}
	
	
	
}
