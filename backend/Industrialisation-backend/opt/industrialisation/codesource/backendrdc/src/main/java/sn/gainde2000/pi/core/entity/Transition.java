/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD
=======
import java.io.Serializable;
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author yougadieng
 */
@Entity
@Table(name = "tr_transition", schema = "")
<<<<<<< HEAD
public class Transition {

    
=======
public class Transition implements Serializable {
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trnId;
    private String trnAction;
<<<<<<< HEAD
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trn_tsk_actuel", referencedColumnName = "tsk_id", insertable = true, updatable = true)
    private Task trnTskactuel;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trn_tsk_suiv", referencedColumnName = "tsk_id", insertable = true, updatable = true)
    private Task trnTskSuiv;
    
=======
    private Long trnTskActuel;
    private Long trnTskSuiv;


>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
    public Long getTrnId() {
        return trnId;
    }

    public void setTrnId(Long trnId) {
        this.trnId = trnId;
    }

    public String getTrnAction() {
        return trnAction;
    }

    public void setTrnAction(String trnAction) {
        this.trnAction = trnAction;
    }

<<<<<<< HEAD
    public Task getTrnTskactuel() {
        return trnTskactuel;
    }

    public void setTrnTskactuel(Task trnTskactuel) {
        this.trnTskactuel = trnTskactuel;
    }

    public Task getTrnTskSuiv() {
        return trnTskSuiv;
    }

    public void setTrnTskSuiv(Task trnTskSuiv) {
        this.trnTskSuiv = trnTskSuiv;
    }
=======
    public Long getTrnTskActuel() {
        return trnTskActuel;
    }

    public void setTrnTskActuel(Long trnTskActuel) {
        this.trnTskActuel = trnTskActuel;
    }

    public Long getTrnTskSuiv() {
        return trnTskSuiv;
    }

    public void setTrnTskSuiv(Long trnTskSuiv) {
        this.trnTskSuiv = trnTskSuiv;
    }
  
   /* @ManyToOne
    @JoinColumn(name = "trn_tsk_actuel", referencedColumnName = "tsk_id", insertable = true, updatable = true)*/
   /* @ManyToOne
    @JoinColumn(name = "trn_tsk_suiv", referencedColumnName = "tsk_id", insertable = true, updatable = true)*/
    

    public Transition() {
    }
    
    @Override
    public String toString() {
        return "Transition{" + "trnId=" + trnId + ", trnAction=" + trnAction + ", trnTskActuel=" + trnTskActuel + ", trnTskSuiv=" + trnTskSuiv + '}';
    }
    
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422

}
