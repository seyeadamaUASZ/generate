
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TraitementRepository extends JpaRepository<Traitement, Long> {
	 @Query("SELECT s from Traitement s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner=:poOwner )")
	    Iterable<Traitement> listTraitement(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from Traitement s WHERE s.owner =:owner ")
	    Iterable<Traitement> listTraitementById(@Param("owner") Long owner);
 @Query("SELECT s from Traitement s WHERE s.id =:id ")
	   Traitement getOneTraitement(@Param("id") Long id);
}