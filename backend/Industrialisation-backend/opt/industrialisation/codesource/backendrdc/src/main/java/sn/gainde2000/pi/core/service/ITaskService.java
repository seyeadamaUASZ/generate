/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.service;

import java.util.List;
<<<<<<< HEAD
import sn.gainde2000.pi.core.entity.Profil;
=======
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
import sn.gainde2000.pi.core.entity.Task;

/**
 *
 * @author jsarr
 */
public interface ITaskService {
    public Task findByTskId(Long tskId);
    public Task findByWorkflowAndProfil(Long workflowId,Long profilId);
    public List<?> getListTaskByTaskIdAndForm(Long taskId,String form);
<<<<<<< HEAD
    public List<Task> getListTaskByTskId(Long tskId);
     public List<Task> getListTaskByTskWkfId(Long tskWkfId);
    
    public Task save(Task t);
    void delete(Long tskId);
=======
    public List<Task> getListTaskByTskId(Long tskId); 
     public List<Task> getListTaskByTskWkfId(Long tskWkfId);
     public List<Task> getAllTask();
     public List<Task> getAllTaskStatusUser(Long tskId,Long profilId); 
    public void updateWrkTask(Task t);
    public Task save(Task t);  
    void delete(Long tskId);
    public Task findByTaskSuivant(Long taskSuiv);
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
}
