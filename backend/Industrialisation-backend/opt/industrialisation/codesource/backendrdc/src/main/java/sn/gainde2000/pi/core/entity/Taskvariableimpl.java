/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.entity;

/**
 *
 * @author jsarr
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

 
@Entity
@Table(name = "taskvariableimpl")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Taskvariableimpl.findAll", query = "SELECT t FROM Taskvariableimpl t")})
public class Taskvariableimpl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
   private Long id;
   private String modificationDate;
   private String name;
   private String processId;
   private Long processInstanceId;
   private Long taskId;
   private Long type;
   private String value;
    
    public Taskvariableimpl() {

    }
    
    public Taskvariableimpl(Long id, String modificationDate, String name, String processId,Long processInstanceId ,Long taskId,Long type,String value) {
        this.id = id;
        this.modificationDate = modificationDate;
        this.name = name;
        this.processId = processId;
        this.processInstanceId = processInstanceId;
        this.taskId = taskId;
        this.type = type;
        this.value = value;
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
    
     public Long getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(Long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    
     public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    
     public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
