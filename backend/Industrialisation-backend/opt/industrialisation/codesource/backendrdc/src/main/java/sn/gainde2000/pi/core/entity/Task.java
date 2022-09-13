/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author indiaye
 */
@Entity(name="Task")
@Table(name = "tp_task", schema = "")
@XmlRootElement 
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT ts FROM Task ts")})
public class Task implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "tsk_id")
    private Long tskId;
    @Size(max = 255)
    @Column(name = "tsk_description")
    private String tskDescription;
    @Size(max = 255)
    @Column(name = "tsk_name")
<<<<<<< HEAD
    private String tskName; 
    @Size(max = 255)
    @Column(name = "tsk_name_suivant")
    private String tskNameSuiv;   
    @Size(max = 255)
    @Column(name = "tsk_container_id")
    private String tskContainerId;    
    @Size(max = 255)
    @Column(name = "tsk_form_name")
    private String tskFormName;
    @Size(max = 255)
    @Column(name = "tsk_owner")
    private String taskowner;
     @Column(name = "tsk_wkf_id")
    private Long tskWkfId;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tsk_tsk_id", referencedColumnName = "tsk_id", insertable = true, updatable = true)
    private Task tskTskSuivId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tsk_pro_id", referencedColumnName = "pro_id", insertable = true, updatable = true)
    private Profil poOwner;
    @Column(name = "tsk_is_first")
    private Boolean tskIsFirst;
    public Task (){
        
    }
    public Task (Long tskId){
        this.tskId = tskId;
    }
     public Task (String tskDescription,String tskName,String taskowner){
        this.tskDescription = tskDescription;
        this.tskName = tskName;
        this.taskowner = taskowner;
    }

=======
    private String tskName;      
    @Size(max = 255)
    @Column(name = "tsk_form_name")
    private String tskFormName;

    @Column(name = "tsk_wkf_id")
    private Long tskWkfId;
    @Column(name = "tsk_status_id")
    private Long tskStatusId;  
    @Column(name = "tsk_suiv_id")          
    private Long tskTskSuivId; 
 
    /*@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tsk_uti_poowner", referencedColumnName = "pro_id", insertable = true, updatable = true)
    private Profil poOwner;*/
    @ManyToOne(targetEntity = Profil.class)
    @JoinColumn(name = "tsk_uti_poowner", referencedColumnName = "pro_id", insertable = true, updatable = true)
    private Profil poOwner;
     
    @Column(name = "tsk_is_first")
    private Boolean tskIsFirst;   

    public Task (){
        
    }
    
    public Task (Long tskId){
        this.tskId = tskId;
    }
    
     public Task (String tskDescription,String tskName,Profil poOwner){

        this.tskDescription = tskDescription;
        this.tskName = tskName;
        this.poOwner = poOwner;
    }
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
    
    public Long getTskId() {
        return tskId;
    }

    public void setTskId(Long tskId) {
        this.tskId = tskId;
    }

    public String getTskDescription() {
        return tskDescription;
    }

    public void setTskDescription(String tskDescription) {
        this.tskDescription = tskDescription;
    }
<<<<<<< HEAD

=======
            
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
    public String getTskName() {
        return tskName;
    }

    public void setTskName(String tskName) {
        this.tskName = tskName;
    }
<<<<<<< HEAD
    public String getTskNameSuiv() {
        return tskNameSuiv;
    }

    public void setTskNameSuiv(String tskNameSuiv) {
        this.tskNameSuiv = tskNameSuiv;
    }
    
   public String getTaskOwner() {
        return taskowner;
    }

    public void setTaskOwner(String taskowner) {
        this.taskowner = taskowner;
    }
    public String getTaskContainerId() {
        return tskContainerId;
    }

    public void setTaskContainerId(String tskContainerId) {
        this.tskContainerId = tskContainerId;
    }
    
    public Long getTskWkfId() {
        return tskWkfId;
    }

    public void setTskWkfId(Long tskWkfId) {
        this.tskWkfId = tskWkfId;
    }

    public Task getTskTskSuivId() {
        return tskTskSuivId;
    }

    public void setTskTskSuivId(Task tskTskSuivId) {
        this.tskTskSuivId = tskTskSuivId;
    }

    public Profil getPoOwner() {
        return poOwner;
    }

    public void setPoOwner(Profil poOwner) {
        this.poOwner = poOwner;
    }
    
    public Boolean getTskIsFirst() {
        return tskIsFirst;
    }

    public void setTskIsFirst(Boolean tskIsFirst) {
        this.tskIsFirst = tskIsFirst;
    }
    public String getTskFormName() {
        return tskFormName;
    }

    public void setTskFormName(String tskFormName) {
        this.tskFormName = tskFormName;
    }
    
    
=======
     
   
    public Long getTskWkfId() {
        return tskWkfId;
    }

    public void setTskWkfId(Long tskWkfId) {
        this.tskWkfId = tskWkfId;
    }
   
    

    public Profil getPoOwner() {
        return poOwner;
    }

    public void setPoOwner(Profil poOwner) {
        this.poOwner = poOwner;
    }
      
    
    public Boolean getTskIsFirst() {
        return tskIsFirst;
    }

    public void setTskIsFirst(Boolean tskIsFirst) {
        this.tskIsFirst = tskIsFirst;
    }
    public String getTskFormName() {
        return tskFormName;
    }

    public void setTskFormName(String tskFormName) {
        this.tskFormName = tskFormName;
    }

    public Long getTskTskSuivId() {
        return tskTskSuivId;
    }

    public void setTskTskSuivId(Long tskTskSuivId) {
        this.tskTskSuivId = tskTskSuivId;
    }

    
    public Long getTskStatusId() {
        return tskStatusId;
    }

    public void setTskStatusId(Long tskStatusId) {
        this.tskStatusId = tskStatusId;
    }
        
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
}
