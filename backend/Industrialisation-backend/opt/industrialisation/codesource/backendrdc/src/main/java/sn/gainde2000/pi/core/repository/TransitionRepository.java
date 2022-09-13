/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.pi.core.entity.Transition;

/**
 *
 * @author yougadieng
 */
@Repository
public interface TransitionRepository extends JpaRepository<Transition,Long>{
<<<<<<< HEAD
    
=======
    Transition findByTrnTskActuel(Long taskId);
>>>>>>> cf53ab128c96f59e7b5aad2c66c2204fb8fd9422
}
