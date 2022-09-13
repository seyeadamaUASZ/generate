package sn.gainde2000.pi.capteurs.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.gainde2000.pi.capteurs.entity.Capteur;
import sn.gainde2000.pi.capteurs.repository.CapteurRepository;
import sn.gainde2000.pi.capteurs.service.ICapteur;

@Service
public class CapteurImpl implements ICapteur{
	@Autowired
   private CapteurRepository repos;
	@Override
	public Capteur addCapteur(Capteur capteur) {
		// TODO Auto-generated method stub
		return repos.save(capteur);
	}

	@Override
	public List<Capteur> listCapteur() {
		// TODO Auto-generated method stub
		return repos.findAll();
	}

	@Override
	public Capteur getCapteur(Long id) {
		// TODO Auto-generated method stub
		return repos.findById(id).get();
	}

	@Override
	public void deleteCapteur(Long id) {
		// TODO Auto-generated method stub
		repos.deleteById(id);
		
	}

	@Override
	public Capteur modifierCapteur(Long id, Capteur capteur) {
		// TODO Auto-generated method stub
		return null;
	}

}
