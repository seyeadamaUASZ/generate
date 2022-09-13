
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Gestionnairecompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface GestionnairecompteRepository extends JpaRepository<Gestionnairecompte, Long> {
	 @Query("SELECT s from Gestionnairecompte s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner.proId=:poOwner )")
	    Iterable<Gestionnairecompte> listGestionnairecompte(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from Gestionnairecompte s WHERE s.owner =:owner ")
	    Iterable<Gestionnairecompte> listGestionnairecompteById(@Param("owner") Long owner);
 @Query("SELECT s from Gestionnairecompte s WHERE s.id =:id ")
	   Gestionnairecompte getOneGestionnairecompte(@Param("id") Long id);

}