
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
@Table(name = "Commande")
public class Commande implements Serializable {
    private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Basic(optional = false)
@NotNull
@Column(name = "id")
private Long id;
@Column(name = "email")
private String email;

@Column(name = "date")
@Temporal(TemporalType.TIMESTAMP)
private Date date;
@Column(name = "quantite")
private Long quantite;

@Column(name = "produit")
private String produit;

public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }   

public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }   

public Long getQuantite() {
        return quantite;
    }
    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }   

public String getProduit() {
        return produit;
    }
    public void setProduit(String produit) {
        this.produit = produit;
    }   

public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}   


}