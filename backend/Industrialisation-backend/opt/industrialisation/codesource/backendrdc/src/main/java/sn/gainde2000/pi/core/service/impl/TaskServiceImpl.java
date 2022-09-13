/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import sn.gainde2000.pi.core.entity.Profil;
=======
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
import sn.gainde2000.pi.core.entity.Task;
import sn.gainde2000.pi.core.repository.TaskRepository;
import sn.gainde2000.pi.core.service.ITaskService;

/**
 *
 * @author jsarr
 */
@Service
<<<<<<< HEAD
public class TaskServiceImpl implements ITaskService{
    
=======
public class TaskServiceImpl implements ITaskService {

>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    EntityManager em;

    @Override
    public Task findByTskId(Long tskId) {
        return taskRepository.findByTskId(tskId);
    }

    @Override
    public List<Task> getListTaskByTskId(Long tskId) {
        return taskRepository.getListTaskByTskId(tskId);
    }

    @Override
    public Task save(Task t) {
<<<<<<< HEAD
         return taskRepository.save(t);
=======
        return taskRepository.save(t);
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
    }

    @Override
    public void delete(Long tskId) {
        taskRepository.deleteById(tskId);
    }

    @Override
    public Task findByWorkflowAndProfil(Long workflowId, Long profilId) {
        return taskRepository.getTaskByWorkflowAndProfilId(workflowId, profilId);
    }

    @Override
    public List<?> getListTaskByTaskIdAndForm(Long taskId, String form) {
<<<<<<< HEAD
        String s = "SELECT *From "+form+"WHERE tsk_id=:taskId";
=======

        String s = "SELECT *From " + form + "WHERE tsk_id=:taskId";

>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
        //String sql = String.format(s, sumKey, tableName, departmentId);
        System.out.println(s);

        List<?> allTask = em.createNativeQuery(s).getResultList();

        return allTask;
<<<<<<< HEAD
    }    
=======

    }

>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422

    @Override
    public List<Task> getListTaskByTskWkfId(Long tskWkfId) {
        return taskRepository.getListTaskByTskWkfId(tskWkfId);
    }

<<<<<<< HEAD
    
=======

    @Override
    public List<Task> getAllTask() {
        // TODO Auto-generated method stub
        return taskRepository.findAll();
    }

    @Override
    public void updateWrkTask(Task t) {
        taskRepository.save(t);
    }

    @Override 
    public List<Task> getAllTaskStatusUser(Long tskId, Long profilId) {
       return taskRepository.getAllTaskStatusUser(tskId,profilId);
    }
 
    public Task findByTaskSuivant(Long taskSuiv) {
        return taskRepository.findByTskTskSuivId(taskSuiv);
    } 

>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
}
