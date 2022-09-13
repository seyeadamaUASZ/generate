/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.entity.InscriptionActive;
import sn.gainde2000.pi.core.repository.InscriptionActiveRepository;
import sn.gainde2000.pi.core.service.IInscriptionActiveService;

/**
 *
 * @author asow
 */
@Service
public class InscriptionActiveServiceImpl implements IInscriptionActiveService {

    @Autowired
    InscriptionActiveRepository inscriptionActiveRepository;

    @Override
    public List<InscriptionActive> getAll() {
        return inscriptionActiveRepository.findAll();
    }

    @Override
    public InscriptionActive findByInaId(Long Id) {
        return inscriptionActiveRepository.findByInaId(Id);
    }

    @Override
    public void update(InscriptionActive inscription) {
        inscriptionActiveRepository.save(inscription);
    }

}
