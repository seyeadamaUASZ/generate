package sn.gainde2000.pi.capteurs.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.gainde2000.pi.capteurs.entity.DonneeRecupereeCapteur;
import sn.gainde2000.pi.capteurs.repository.DonneeCapteurRepository;
import sn.gainde2000.pi.capteurs.service.IDonneeRecuperee;

@Service
public class DonneeRecupereeCapteurImpl implements IDonneeRecuperee {
	@Autowired
	private DonneeCapteurRepository repos;

	@Override
	public DonneeRecupereeCapteur addDonneeRecuperee(DonneeRecupereeCapteur donneeRecupereeCapteur) {
		// TODO Auto-generated method stub
		return repos.save(donneeRecupereeCapteur);
	}

	@Override
	public List<DonneeRecupereeCapteur> listDonneeRecuperee() {
		// TODO Auto-generated method stub
		return repos.findAll();
	}

	@Override
	public DonneeRecupereeCapteur getDonneeRecuperee(Long id) {
		// TODO Auto-generated method stub
		return repos.findById(id).get();
	}

	@Override
	public List<DonneeRecupereeCapteur> listDonneeCapteurRecuperee(Long id) {
		// TODO Auto-generated method stub
		return repos.listeDonneeCapteur(id);
	}

}
