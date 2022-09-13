
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
@Table(name = "Inscription")
public class Inscription implements Serializable {
    private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Basic(optional = false)
@NotNull
@Column(name = "id")
private Long id;
@Column(name = "profile", columnDefinition = "MEDIUMBLOB")
private byte[] profile;
@Column(name = "extention")
private String extention ;

@Column(name = "prenom")
private String prenom;

@Column(name = "datenaiss")
@Temporal(TemporalType.TIMESTAMP)
private Date datenaiss;
@Column(name = "nom")
private String nom;

public byte[] getProfile() {
        return profile;
    }
    public void setProfile(byte[] profile) {
        this.profile = profile;
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

public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }   

public Date getDatenaiss() {
        return datenaiss;
    }
    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }   

public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }   


}