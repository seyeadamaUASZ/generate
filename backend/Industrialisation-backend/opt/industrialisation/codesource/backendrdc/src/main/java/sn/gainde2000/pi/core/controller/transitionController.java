/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.controller;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;
import sn.gainde2000.pi.core.ServeurResponse.ServeurResponse;
<<<<<<< HEAD
import sn.gainde2000.pi.core.entity.Transition;
=======
import sn.gainde2000.pi.core.entity.Task;
import sn.gainde2000.pi.core.entity.Transition;
import sn.gainde2000.pi.core.service.impl.TaskServiceImpl;
import sn.gainde2000.pi.core.service.impl.TransitionServiceImpl;
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422

/**
 *
 * @author yougadieng
 */
@RestController
@CrossOrigin("*")
@RequestMapping("transition")
<<<<<<< HEAD
public class transitionController {
    @PostMapping("create")
    public ServeurResponse create(@RequestBody Transition transition){
        ServeurResponse response=new ServeurResponse();
        return response;
    }
    
=======
public class TransitionController {

    @Autowired
    TransitionServiceImpl transitionService;
    @Autowired
    TaskServiceImpl taskService;

    @PostMapping("create")
    public ServeurResponse createTransition(@RequestBody Transition transition) {
        System.out.println("------------transition-----" + transition.toString());
        ServeurResponse response = new ServeurResponse();
        transitionService.create(transition);
        return response;
    }

    @GetMapping("statusAfterExecution/{taskId}")
    public ServeurResponse getStatusTask(@PathVariable Long taskId) {
        ServeurResponse response = new ServeurResponse();
        Transition t = transitionService.getTransitionSuiv(taskId);
        if (t != null) {
            Task taskStatus = taskService.findByTskId(t.getTrnTskSuiv());
            response.setData(taskStatus.getTskStatusId());
            response.setStatut(true);
            response.setDescription("statut recupere avec succès");
        } else {
            response.setStatut(false);
            response.setDescription("statut non recupere");
        }

        return response;
    }
<<<<<<< HEAD
    
    
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
=======

>>>>>>> 2a8b8243cda7dac57b35222ea8b77b41216b6658
}
