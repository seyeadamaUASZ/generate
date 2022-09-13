
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Gestionnairecompte;
import sn.gainde2000.pi.core.service.IGestionnairecompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.GestionnairecompteRepository;
import java.util.List;
import java.util.Optional;

@Service
public class GestionnairecompteServiceImpl implements IGestionnairecompteService {

    @Autowired
    private GestionnairecompteRepository gestionnairecompteRepository;

    @Override
    public List<Gestionnairecompte> findAll() {

        return gestionnairecompteRepository.findAll();
    }
    @Override
    public Gestionnairecompte save(Gestionnairecompte gestionnairecompte) {

        return gestionnairecompteRepository.save(gestionnairecompte);
    }
    @Override
    public void delete(Gestionnairecompte gestionnairecompte) {

  gestionnairecompteRepository.delete(gestionnairecompte);
    }
	@Override
	public Optional<Gestionnairecompte> findById(Long id) {
		return gestionnairecompteRepository.findById(id);
	}@Override
	public Gestionnairecompte getOneGestionnairecompte(Long id) {
		return gestionnairecompteRepository.getOneGestionnairecompte(id);
	}
@Override
public Iterable<Gestionnairecompte> listGestionnairecompte(Long poOwner) {
    return gestionnairecompteRepository.listGestionnairecompte(poOwner);
}
@Override
public Iterable<Gestionnairecompte> listGestionnairecompteById(Long owner) {
    return gestionnairecompteRepository.listGestionnairecompteById(owner);
}}