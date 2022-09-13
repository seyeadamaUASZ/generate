/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.entity;

import com.fasterxml.jackson.databind.JsonNode;
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
import org.hibernate.annotations.TypeDef;

/**
 *
 * @author jsarr
 */
@Entity(name = "JbpmTaskEntree")
@Table(name = "td_jbpmtaskentree")
@NamedQueries({
    @NamedQuery(name = "JbpmTaskEntree.findAll", query = "SELECT j FROM JbpmTaskEntree j")})
public class JbpmTaskEntree implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "container")
    private String container;
    @Size(max = 255)
    @Column(name = "prossid")
    private String prossid;
    @Size(max = 255)
    @Column(name = "jbpmusername")
    private String jbpmusername;
    @Size(max = 255)
    @Column(name = "jbpmfichier")
    private String jbpmfichier;
    @Column(name = "taskId")
    private Long taskId;
    @Column(name = "processInstId")
    private Long processInstId;
    @Size(max = 255)
      
@Column(columnDefinition = "JSON")
    private String taskformvalues;
    
    public JbpmTaskEntree (){
    
        }
    
    public JbpmTaskEntree (Long taskId){
    
        this.taskId = taskId;
        }
    
    public JbpmTaskEntree (Long id,Long taskId,String jbpmusername,Long processInstId,String container,String prossid,String taskformvalues,String jbpmfichier){
        this.id = id;
        this.taskId = taskId;
        this.processInstId = processInstId;
        this.container = container;
        this.prossid = prossid;
        this.taskformvalues = taskformvalues;
        this.jbpmusername = jbpmusername;
        this.jbpmfichier = jbpmfichier;
        
        }
    
       public Long getId() {
        return id;
        }

        public void setId(Long id) {
        this.id = id;
        }
        
        public Long getTaskId() {
        return taskId;
        }

        public void setTaskId(Long taskId) {
        this.taskId = taskId;
        }
        public Long getProcessInstId() {
        return processInstId;
        }

        public void setProcessInstId(Long processInstId) {
        this.processInstId = processInstId;
        }
        
         public String getJbpmusername() {
        return jbpmusername;
        }

        public void setJbpmusername(String jbpmusername) {
        this.jbpmusername = jbpmusername;
        }
        
        public String getContainer() {
        return container;
        }
        
        public void setcontainer(String container ) {
        this.container = container;
        }
        
        public String getProssid() {
        return prossid;
        }
        
        public void setProssid(String prossid ) {
        this.prossid = prossid;
        }
        
        public String getTaskformvalues() {
        return taskformvalues;
        }
        
        public void setTaskformvalues(String taskformvalues ) {
        this.taskformvalues = taskformvalues;
        }
        
        public String getJbpmfichier() {
        return jbpmfichier;
        }
        
        public void setJbpmfichier(String jbpmfichier ) {
        this.jbpmfichier = jbpmfichier;
        }
    
}
