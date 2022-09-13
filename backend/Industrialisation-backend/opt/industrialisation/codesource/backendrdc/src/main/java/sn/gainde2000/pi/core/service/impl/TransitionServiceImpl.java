/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.service.impl;

import java.util.List;
import javax.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.entity.Transition;
import sn.gainde2000.pi.core.repository.TransitionRepository;
import sn.gainde2000.pi.core.service.ITransition;

/**
 *
 * @author yougadieng
 */
@Service
public class TransitionServiceImpl implements ITransition {

    @Autowired
    public TransitionRepository transitionRepo;

    @Override
    public List<Transition> getAllTransaction() {
        return transitionRepo.findAll();
    }

    @Override
    public void create(Transition transition) {
        transitionRepo.save(transition);
    }

<<<<<<< HEAD
=======
    @Override
    public Transition getTransitionSuiv(Long taskId) {
     return  transitionRepo.findByTrnTskActuel(taskId);
    }

>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
}
