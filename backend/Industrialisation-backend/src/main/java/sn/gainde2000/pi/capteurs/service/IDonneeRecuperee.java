package sn.gainde2000.pi.capteurs.service;

import java.util.List;

import sn.gainde2000.pi.capteurs.entity.DonneeRecupereeCapteur;

public interface IDonneeRecuperee {
	public DonneeRecupereeCapteur addDonneeRecuperee(DonneeRecupereeCapteur donneeRecupereeCapteur);
	public List<DonneeRecupereeCapteur> listDonneeRecuperee();
	public DonneeRecupereeCapteur getDonneeRecuperee(Long id);
	public List<DonneeRecupereeCapteur> listDonneeCapteurRecuperee(Long id);

}
