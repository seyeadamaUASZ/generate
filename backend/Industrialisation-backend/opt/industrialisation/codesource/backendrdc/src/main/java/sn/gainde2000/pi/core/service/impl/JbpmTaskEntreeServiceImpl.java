/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.entity.JbpmTaskEntree;
import sn.gainde2000.pi.core.repository.JbpmTaskEntreeRepository;
import sn.gainde2000.pi.core.service.IJbpmTaskEntreeService;

/**
 *
 * @author jsarr
 */
@Service
public class JbpmTaskEntreeServiceImpl implements IJbpmTaskEntreeService{

    @Autowired
    JbpmTaskEntreeRepository jbpmTaskEntreeRepository;
    
    @Override
    public List<JbpmTaskEntree> getListJbpmTaskEntree() {
       return jbpmTaskEntreeRepository.findAll();
    }

    @Override
    public JbpmTaskEntree save(JbpmTaskEntree j) {
        return jbpmTaskEntreeRepository.save(j);
    }

    @Override
    public JbpmTaskEntree findByTaskId(String TaskId) {
         return jbpmTaskEntreeRepository.findByTaskId(TaskId);
    }
    
}
