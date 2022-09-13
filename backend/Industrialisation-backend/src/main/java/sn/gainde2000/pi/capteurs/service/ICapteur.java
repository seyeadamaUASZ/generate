package sn.gainde2000.pi.capteurs.service;

import java.util.List;

import sn.gainde2000.pi.capteurs.entity.Capteur;

public interface ICapteur {
 public Capteur addCapteur(Capteur capteur);
 public List<Capteur> listCapteur();
 public Capteur getCapteur(Long id);
 public void deleteCapteur(Long id);
 public Capteur modifierCapteur(Long id,Capteur capteur);
}
