
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
@Table(name = "Ouverturecompte")
public class Ouverturecompte implements Serializable {
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
@Column(name = "nom")
private String nom;

@Column(name = "prenom")
private String prenom;

@Column(name = "adresse")
private String adresse;

@Column(name = "datenaissance")
@Temporal(TemporalType.TIMESTAMP)
private Date datenaissance;
@Column(name = "telephone")
private String telephone;

@Column(name = "typecompte")
private String typecompte;

@Column(name = "typeservice")
private String typeservice;

@Column(name = "cni", columnDefinition = "MEDIUMBLOB")
private byte[] cni;

@Column(name = "certificationresidence", columnDefinition = "MEDIUMBLOB")
private byte[] certificationresidence;

public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }   

public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }   

public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }   

public Date getDatenaissance() {
        return datenaissance;
    }
    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }   

public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }   

public String getTypecompte() {
        return typecompte;
    }
    public void setTypecompte(String typecompte) {
        this.typecompte = typecompte;
    }   

public String getTypeservice() {
        return typeservice;
    }
    public void setTypeservice(String typeservice) {
        this.typeservice = typeservice;
    }   

public byte[] getCni() {
        return cni;
    }
    public void setCni(byte[] cni) {
        this.cni = cni;
    }   
public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}   

public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}  

public byte[] getCertificationresidence() {
        return certificationresidence;
    }
    public void setCertificationresidence(byte[] certificationresidence) {
        this.certificationresidence = certificationresidence;
    }   
public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}   

public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
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