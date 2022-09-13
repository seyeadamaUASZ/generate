/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.entity.Taskvariableimpl;
import sn.gainde2000.pi.core.repository.JbpmTaskDonneeRepository;

/**
 *
 * @author jsarr
 */
@Service
public class IJbpmTaskDonneeService {
    
    @Autowired
    JbpmTaskDonneeRepository jbpmTaskDonneeRepository;
    
    public Taskvariableimpl GetTaskId(Long taskId){
     return jbpmTaskDonneeRepository.findAllById(taskId);

    }
    public Taskvariableimpl GetProcessInstanceId(Long processInstanceId){
    return jbpmTaskDonneeRepository.findAllById(processInstanceId);
    } 
    
    public List<Taskvariableimpl> GetJbpmProcessInstanceId(){
        return jbpmTaskDonneeRepository.findAll();
    }
}
