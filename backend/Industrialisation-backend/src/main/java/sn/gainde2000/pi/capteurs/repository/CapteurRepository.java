package sn.gainde2000.pi.capteurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.gainde2000.pi.capteurs.entity.Capteur;

public interface CapteurRepository extends JpaRepository<Capteur, Long>{

}
