/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 
import org.springframework.stereotype.Repository; 
import sn.gainde2000.pi.core.entity.Taskvariableimpl;
/**
 *
 * @author jsarr
 */
@Repository
public interface JbpmTaskDonneeRepository extends JpaRepository<Taskvariableimpl, Long>{

    public Taskvariableimpl findAllById(Long taskId);
    
   /* public Taskvariableimpl findByTaskId(Long taskId);
    public Taskvariableimpl findByProcessInstanceId(Long processInstanceId);
    
    @Query("select t from taskvariableimpl t")
    public List<Taskvariableimpl> GetJbpmProcessInstanceId();*/
    
}
