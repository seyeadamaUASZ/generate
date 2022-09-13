/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.service;

import java.util.List;
import sn.gainde2000.pi.core.entity.Transition;

/**
 *
 * @author yougadieng
 */
public interface ITransition {
    public void create(Transition transition);
    public List<Transition> getAllTransaction();
<<<<<<< HEAD
    
=======
    public Transition  getTransitionSuiv(Long taskId);    
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
}
