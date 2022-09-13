package sn.gainde2000.pi.capteurs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sn.gainde2000.pi.capteurs.entity.DonneeRecupereeCapteur;

public interface DonneeCapteurRepository extends JpaRepository<DonneeRecupereeCapteur, Long>{
  
	@Query("select d from DonneeRecupereeCapteur d,Capteur c where d.capteur.idCapteur=c.idCapteur and c.idCapteur=id")
	public List<DonneeRecupereeCapteur> listeDonneeCapteur(@Param("id") Long id);
}
