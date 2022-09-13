
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
@Table(name = "Soumission")
public class Soumission implements Serializable {
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
@Column(name = "carte grise", columnDefinition = "MEDIUMBLOB")
private byte[] carte grise;
@Column(name = "extention")
private String extention ;

@Column(name = "emission co2")
private String emission co2;

@Column(name = "numeros d'immatriculation")
private String numeros d'immatriculation;

@Column(name = "type variante")
private String type variante;

@Column(name = "couleur dominante")
private String couleur dominante;

@Column(name = "energie")
private String energie;

@Column(name = "marque du vehicule")
private String marque du vehicule;

public byte[] getCarte grise() {
        return carte grise;
    }
    public void setCarte grise(byte[] carte grise) {
        this.carte grise = carte grise;
    }   
public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}  

public String getEmission co2() {
        return emission co2;
    }
    public void setEmission co2(String emission co2) {
        this.emission co2 = emission co2;
    }   

public String getNumeros d'immatriculation() {
        return numeros d'immatriculation;
    }
    public void setNumeros d'immatriculation(String numeros d'immatriculation) {
        this.numeros d'immatriculation = numeros d'immatriculation;
    }   

public String getType variante() {
        return type variante;
    }
    public void setType variante(String type variante) {
        this.type variante = type variante;
    }   

public String getCouleur dominante() {
        return couleur dominante;
    }
    public void setCouleur dominante(String couleur dominante) {
        this.couleur dominante = couleur dominante;
    }   

public String getEnergie() {
        return energie;
    }
    public void setEnergie(String energie) {
        this.energie = energie;
    }   

public String getMarque du vehicule() {
        return marque du vehicule;
    }
    public void setMarque du vehicule(String marque du vehicule) {
        this.marque du vehicule = marque du vehicule;
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