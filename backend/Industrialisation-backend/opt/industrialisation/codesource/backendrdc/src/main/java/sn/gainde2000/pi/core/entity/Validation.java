
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
@Table(name = "Validation")
public class Validation implements Serializable {
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
@Column(name = "valider")
private String valider;

@Column(name = "total")
private String total;

@Column(name = "frais_dossier")
private String frais_dossier;

@Column(name = "taxe_co2")
private String taxe_co2;

@Column(name = "taxe_parafiscale")
private String taxe_parafiscale;

@Column(name = "taxe_regional")
private String taxe_regional;

public String getValider() {
        return valider;
    }
    public void setValider(String valider) {
        this.valider = valider;
    }   

public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }   

public String getFrais_dossier() {
        return frais_dossier;
    }
    public void setFrais_dossier(String frais_dossier) {
        this.frais_dossier = frais_dossier;
    }   

public String getTaxe_co2() {
        return taxe_co2;
    }
    public void setTaxe_co2(String taxe_co2) {
        this.taxe_co2 = taxe_co2;
    }   

public String getTaxe_parafiscale() {
        return taxe_parafiscale;
    }
    public void setTaxe_parafiscale(String taxe_parafiscale) {
        this.taxe_parafiscale = taxe_parafiscale;
    }   

public String getTaxe_regional() {
        return taxe_regional;
    }
    public void setTaxe_regional(String taxe_regional) {
        this.taxe_regional = taxe_regional;
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