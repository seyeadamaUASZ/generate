
package sn.gainde2000.pi.core.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import sn.gainde2000.pi.core.*;
import java.util.Date;
@Entity
@Table(name = "Traitement")
public class Traitement implements Serializable {
    private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Basic(optional = false)
@NotNull
@Column(name = "id")
private Long id;
 private Long status; 
 private Long poOwner ;
  private Long owner ;
 private Long idLink; 
@Column(name = "annee_registre")
private String annee_registre;

@Column(name = "date_naissance")
private String date_naissance;

@Column(name = "heure_naissance")
private String heure_naissance;

@Column(name = "lieu_naissance")
private String lieu_naissance;

@Column(name = "nom")
private String nom;

@Column(name = "nom_mere")
private String nom_mere;

@Column(name = "num_registre")
private String num_registre;

@Column(name = "prenom")
private String prenom;

@Column(name = "prenom_mere")
private String prenom_mere;

@Column(name = "prenom_pere")
private String prenom_pere;

@Column(name = "sexe")
private String sexe;

@Column(name = "validation1")
private String validation1;

@Column(name = "fichier", columnDefinition = "MEDIUMBLOB")
private byte[] fichier;
@Column(name = "extention")
private String extention ;

public String getAnnee_registre() {
        return annee_registre;
    }
    public void setAnnee_registre(String annee_registre) {
        this.annee_registre = annee_registre;
    }   

public String getDate_naissance() {
        return date_naissance;
    }
    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }   

public String getHeure_naissance() {
        return heure_naissance;
    }
    public void setHeure_naissance(String heure_naissance) {
        this.heure_naissance = heure_naissance;
    }   

public String getLieu_naissance() {
        return lieu_naissance;
    }
    public void setLieu_naissance(String lieu_naissance) {
        this.lieu_naissance = lieu_naissance;
    }   

public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }   

public String getNom_mere() {
        return nom_mere;
    }
    public void setNom_mere(String nom_mere) {
        this.nom_mere = nom_mere;
    }   

public String getNum_registre() {
        return num_registre;
    }
    public void setNum_registre(String num_registre) {
        this.num_registre = num_registre;
    }   

public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }   

public String getPrenom_mere() {
        return prenom_mere;
    }
    public void setPrenom_mere(String prenom_mere) {
        this.prenom_mere = prenom_mere;
    }   

public String getPrenom_pere() {
        return prenom_pere;
    }
    public void setPrenom_pere(String prenom_pere) {
        this.prenom_pere = prenom_pere;
    }   

public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }   

public String getValidation1() {
        return validation1;
    }
    public void setValidation1(String validation1) {
        this.validation1 = validation1;
    }   

public byte[] getFichier() {
        return fichier;
    }
    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }   
public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}  

 public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}   

public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}   

public Long getPoOwner() {
		return poOwner;
	}
	public void setPoOwner(Long poOwner) {
		this.poOwner = poOwner;
	}   

public Long getOwner() {
		return owner;
	}
	public void setOwner(Long owner) {
		this.owner = owner;
	}   

public Long getIdLink() {
		return idLink;
	}
	public void setIdLink(Long idLink) {
		this.idLink = idLink;
	}   


}