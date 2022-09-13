/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yougadieng
 */
@Entity
@Table(name = "tp_pays", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p")})
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "pys_id")
    private Long pysId;
    @Size(max = 255)
    @Column(name = "pys_code")
    private String pysCode;
    @Size(max = 255)
    @Column(name = "pys_icone")
    private String pysIcone;
    @Size(max = 255)
    @Column(name = "pys_nom")
    private String pysNom;

    public Pays() {
    }

    public Pays(Long pysId) {
        this.pysId = pysId;
    }

    public Long getPysId() {
        return pysId;
    }

    public void setPysId(Long pysId) {
        this.pysId = pysId;
    }

    public String getPysCode() {
        return pysCode;
    }

    public void setPysCode(String pysCode) {
        this.pysCode = pysCode;
    }

    public String getPysIcone() {
        return pysIcone;
    }

    public void setPysIcone(String pysIcone) {
        this.pysIcone = pysIcone;
    }

    public String getPysNom() {
        return pysNom;
    }

    public void setPysNom(String pysNom) {
        this.pysNom = pysNom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pysId != null ? pysId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.pysId == null && other.pysId != null) || (this.pysId != null && !this.pysId.equals(other.pysId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sn.gainde2000.pi.core.entity.Pays[ pysId=" + pysId + " ]";
    }
    
}
