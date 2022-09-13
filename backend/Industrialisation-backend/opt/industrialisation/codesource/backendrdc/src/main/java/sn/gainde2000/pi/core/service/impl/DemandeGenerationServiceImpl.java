
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.DemandeGeneration;
import sn.gainde2000.pi.core.service.IDemandeGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.DemandeGenerationRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeGenerationServiceImpl implements IDemandeGenerationService {

    @Autowired
    private DemandeGenerationRepository demandegenerationRepository;

    @Override
    public List<DemandeGeneration> findAll() {

        return demandegenerationRepository.findAll();
    }
    @Override
    public DemandeGeneration save(DemandeGeneration demandegeneration) {

        return demandegenerationRepository.save(demandegeneration);
    }
    @Override
    public void delete(DemandeGeneration demandegeneration) {

  demandegenerationRepository.delete(demandegeneration);
    }
	@Override
	public Optional<DemandeGeneration> findById(Long id) {
		return demandegenerationRepository.findById(id);
	}@Override
	public DemandeGeneration getOneDemandeGeneration(Long id) {
		return demandegenerationRepository.getOneDemandeGeneration(id);
	}
@Override
public Iterable<DemandeGeneration> listDemandeGeneration(Long poOwner) {
    return demandegenerationRepository.listDemandeGeneration(poOwner);
}
@Override
public Iterable<DemandeGeneration> listDemandeGenerationById(Long owner) {
    return demandegenerationRepository.listDemandeGenerationById(owner);
}}