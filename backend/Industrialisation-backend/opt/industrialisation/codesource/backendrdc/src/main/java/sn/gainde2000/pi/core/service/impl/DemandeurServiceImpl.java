
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Demandeur;
import sn.gainde2000.pi.core.service.IDemandeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.DemandeurRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeurServiceImpl implements IDemandeurService {

    @Autowired
    private DemandeurRepository demandeurRepository;

    @Override
    public List<Demandeur> findAll() {

        return demandeurRepository.findAll();
    }
    @Override
    public Demandeur save(Demandeur demandeur) {

        return demandeurRepository.save(demandeur);
    }
    @Override
    public void delete(Demandeur demandeur) {

  demandeurRepository.delete(demandeur);
    }
	@Override
	public Optional<Demandeur> findById(Long id) {
		return demandeurRepository.findById(id);
	}
@Override
	public Demandeur getOneDemandeur(Long id) {
		// TODO Auto-generated method stub
		return demandeurRepository.getOneDemandeur(id);
	}
@Override
public Iterable<Demandeur> listDemandeur(Long poOwner) {
    return demandeurRepository.listDemandeur(poOwner);
}
@Override
public Iterable<Demandeur> listDemandeurById(Long owner) {
    return demandeurRepository.listDemandeurById(owner);
}}