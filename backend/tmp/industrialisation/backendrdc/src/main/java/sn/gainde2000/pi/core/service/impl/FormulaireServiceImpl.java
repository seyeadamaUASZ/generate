package sn.gainde2000.pi.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.gainde2000.pi.core.entity.Formulaire;
import sn.gainde2000.pi.core.repository.FormulaireRepository;
import sn.gainde2000.pi.core.service.IFormulaireService;

@Service
public class FormulaireServiceImpl implements IFormulaireService {

    @Autowired
    FormulaireRepository formuliareRepository;

    @Override
    public Formulaire findByfrmId(Long frmId) {

        return formuliareRepository.findByfrmId(frmId);
    }

    @Override
    public List<Formulaire> findByfrmAppId(Long id) {
        // TODO Auto-generated method stub
        return formuliareRepository.findByfrmAppId(id);
    }

    @Override
    public List<Formulaire> listformnotgenerate() {
        // TODO Auto-generated method stub
        return formuliareRepository.listformnotgenerate();
    }

    @Override
    public List<Formulaire> listformgenerate() {
        // TODO Auto-generated method stub
        return formuliareRepository.listformgenerate();
    }

    @Override
    public List<Formulaire> getAllFormulaire() {
        // TODO Auto-generated method stub
        return formuliareRepository.findAll();
    }

    @Override
    public Formulaire save(Formulaire formulaire) {
        // TODO Auto-generated method stub
        return formuliareRepository.save(formulaire);
    }

    @Override
    public Optional<Formulaire> findById(Long id) {
        // TODO Auto-generated method stub
        return formuliareRepository.findById(id);
    }

    @Override
    public void delete(Formulaire formulaire) {
        formuliareRepository.delete(formulaire);

    }

    public List<Formulaire> getAllFormulaireLibreValider() {
        return formuliareRepository.listformLibreEtValider();
    }

     @Override
     public void lierFormulaire(String idApp, String idFormulaire) {
          formuliareRepository.lierFormulaire(idApp, idFormulaire);
     }

     @Override
     public void enleverLiasonFormulaire(String idFormulaire) {
          formuliareRepository.enleverLiaisonidFormulaire(idFormulaire);
     }

     @Override
     public List<Formulaire> listformByAppId(Long id) {
          return formuliareRepository.listformByApp(id);
     }

    @Override
    public List<Formulaire> listformByAppIdOulibre(Long id) {
        return formuliareRepository.listformByAppOuLibre(id);
    }

	@Override
	public Formulaire findByfrmNom(String frmNom) {
		// TODO Auto-generated method stub
		return formuliareRepository.findByfrmNom(frmNom) ;
	}

     @Override
     public int NombreFormulaire() {
          return formuliareRepository.nombreFormulaireGenerate();
     }

}
