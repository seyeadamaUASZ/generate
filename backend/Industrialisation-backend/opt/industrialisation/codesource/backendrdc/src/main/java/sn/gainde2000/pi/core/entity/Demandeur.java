
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
@Table(name = "Demandeur")
public class Demandeur implements Serializable {
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

@Column(name = "email")
private String email;

@Column(name = "num_registre")
private String num_registre;

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

public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }   

public String getNum_registre() {
        return num_registre;
    }
    public void setNum_registre(String num_registre) {
        this.num_registre = num_registre;
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