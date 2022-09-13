/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author asow
 */
@Entity
@Table(name="tp_inscriptionactive")
public class InscriptionActive implements Serializable {
    
        private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inaId;
    private Boolean inaIsActive;
    private String description;

    public InscriptionActive() {
    }

    public InscriptionActive(Long inaId) {
        this.inaId = inaId;
    }

    public Long getInaId() {
        return inaId;
    }

    public void setInaId(Long inaId) {
        this.inaId = inaId;
    }

    public Boolean getInaIsActive() {
        return inaIsActive;
    }

    public void setInaIsActive(Boolean inaIsActive) {
        this.inaIsActive = inaIsActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    @Override
    public String toString() {
        return "InscriptionActive{" + "inaId=" + inaId + ", inaIsActive=" + inaIsActive + '}';
    }
    
}
