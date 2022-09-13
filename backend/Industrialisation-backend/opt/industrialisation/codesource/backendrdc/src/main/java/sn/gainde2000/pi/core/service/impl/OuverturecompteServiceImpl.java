
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Ouverturecompte;
import sn.gainde2000.pi.core.service.IOuverturecompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.OuverturecompteRepository;
import java.util.List;
import java.util.Optional;

@Service
public class OuverturecompteServiceImpl implements IOuverturecompteService {

    @Autowired
    private OuverturecompteRepository ouverturecompteRepository;

    @Override
    public List<Ouverturecompte> findAll() {

        return ouverturecompteRepository.findAll();
    }
    @Override
    public Ouverturecompte save(Ouverturecompte ouverturecompte) {

        return ouverturecompteRepository.save(ouverturecompte);
    }
    @Override
    public void delete(Ouverturecompte ouverturecompte) {

  ouverturecompteRepository.delete(ouverturecompte);
    }
	@Override
	public Optional<Ouverturecompte> findById(Long id) {
		return ouverturecompteRepository.findById(id);
	}@Override
	public Ouverturecompte getOneOuverturecompte(Long id) {
		return ouverturecompteRepository.getOneOuverturecompte(id);
	}
@Override
public Iterable<Ouverturecompte> listOuverturecompte(Long poOwner) {
    return ouverturecompteRepository.listOuverturecompte(poOwner);
}
@Override
public Iterable<Ouverturecompte> listOuverturecompteById(Long owner) {
    return ouverturecompteRepository.listOuverturecompteById(owner);
}
@Override
    public Iterable<Ouverturecompte> listOuverturecompteTraitesAssistantclient(Long poOwner) {
        return this.ouverturecompteRepository.listOuverturecompteTraitesassistantclient(poOwner);
    }

@Override
    public Iterable<Ouverturecompte> listOuverturecompteTraitesGestionnairecompte(Long poOwner) {
        return this.ouverturecompteRepository.listOuverturecompteTraitesgestionnairecompte(poOwner);
    }
}