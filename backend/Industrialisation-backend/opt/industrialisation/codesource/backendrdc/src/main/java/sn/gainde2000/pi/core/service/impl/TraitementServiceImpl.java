
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Traitement;
import sn.gainde2000.pi.core.service.ITraitementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.TraitementRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TraitementServiceImpl implements ITraitementService {

    @Autowired
    private TraitementRepository traitementRepository;

    @Override
    public List<Traitement> findAll() {

        return traitementRepository.findAll();
    }
    @Override
    public Traitement save(Traitement traitement) {

        return traitementRepository.save(traitement);
    }
    @Override
    public void delete(Traitement traitement) {

  traitementRepository.delete(traitement);
    }
	@Override
	public Optional<Traitement> findById(Long id) {
		return traitementRepository.findById(id);
	}
@Override
	public Traitement getOneTraitement(Long id) {
		// TODO Auto-generated method stub
		return traitementRepository.getOneTraitement(id);
	}
@Override
public Iterable<Traitement> listTraitement(Long poOwner) {
    return traitementRepository.listTraitement(poOwner);
}
@Override
public Iterable<Traitement> listTraitementById(Long owner) {
    return traitementRepository.listTraitementById(owner);
}}