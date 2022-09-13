
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
@Table(name = "Demande")
public class Demande implements Serializable {
    private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Basic(optional = false)
@NotNull
@Column(name = "id")
private Long id;
@Column(name = "nom")
private String nom;

@Column(name = "prenom")
private String prenom;

@Column(name = "cni", columnDefinition = "MEDIUMBLOB")
private byte[] cni;
@Column(name = "extention")
private String extention ;

@Column(name = "passport", columnDefinition = "MEDIUMBLOB")
private byte[] passport;
@Column(name = "extention")
private String extention ;

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

public byte[] getPassport() {
        return passport;
    }
    public void setPassport(byte[] passport) {
        this.passport = passport;
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


}