
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Demande;
import sn.gainde2000.pi.core.service.IDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.DemandeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeServiceImpl implements IDemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Override
    public List<Demande> findAll() {

        return demandeRepository.findAll();
    }
    @Override
    public Demande save(Demande demande) {

        return demandeRepository.save(demande);
    }
    @Override
    public void delete(Demande demande) {

  demandeRepository.delete(demande);
    }
	@Override
	public Optional<Demande> findById(Long id) {
		return demandeRepository.findById(id);
	}}