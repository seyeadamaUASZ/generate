/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.gainde2000.pi.core.entity.Profil;
import sn.gainde2000.pi.core.entity.Task;

/**
 *
 * @author jsarr
 */
@Repository
public interface TaskRepository extends JpaRepository<Task , Long>{
     
     public Task findByTskId(Long tskId);
<<<<<<< HEAD
     @Query("select ts from Task ts where ts.tskWkfId =:tskId") 
     public List<Task> getListTaskByTskWkfId(@Param("tskId") Long tskId);
      @Query("select ts from Task ts where ts.tskId =:tskId") 
     public List<Task> getListTaskByTskId(@Param("tskId") Long tskId);
     @Query("select ts from Task ts where ts.tskWkfId =:workflowId and ts.poOwner=:profilId") 
     public Task getTaskByWorkflowAndProfilId(@Param("workflowId") Long workflowId,@Param("profilId") Long profilId);
    
=======

     public Task findByTskTskSuivId(Long tskId);

     @Query("select ts from Task ts where ts.tskWkfId =:tskId") 
     public List<Task> getListTaskByTskWkfId(@Param("tskId") Long tskId);
     @Query("select ts from Task ts where ts.tskId =:tskId") 
     public List<Task> getListTaskByTskId(@Param("tskId") Long tskId);
     @Query("select ts from Task ts where ts.tskWkfId =:workflowId and ts.poOwner=:profilId") 
     public Task getTaskByWorkflowAndProfilId(@Param("workflowId") Long workflowId,@Param("profilId") Long profilId);
     @Query("select ts from Task ts where ts.tskId =:tskId and ts.poOwner=:profilId") 
     public List<Task> getAllTaskStatusUser(@Param("tskId") Long tskId,@Param("profilId") Long profilId);
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
}
