
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Inscription;
import sn.gainde2000.pi.core.service.IInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.InscriptionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionServiceImpl implements IInscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Override
    public List<Inscription> findAll() {

        return inscriptionRepository.findAll();
    }
    @Override
    public Inscription save(Inscription inscription) {

        return inscriptionRepository.save(inscription);
    }
    @Override
    public void delete(Inscription inscription) {

  inscriptionRepository.delete(inscription);
    }
	@Override
	public Optional<Inscription> findById(Long id) {
		return inscriptionRepository.findById(id);
	}}