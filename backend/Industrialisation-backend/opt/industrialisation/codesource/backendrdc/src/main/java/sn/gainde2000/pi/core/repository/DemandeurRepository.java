
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Demandeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface DemandeurRepository extends JpaRepository<Demandeur, Long> {
	 @Query("SELECT s from Demandeur s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner=:poOwner )")
	    Iterable<Demandeur> listDemandeur(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from Demandeur s WHERE s.owner =:owner ")
	    Iterable<Demandeur> listDemandeurById(@Param("owner") Long owner);
 @Query("SELECT s from Demandeur s WHERE s.id =:id ")
	   Demandeur getOneDemandeur(@Param("id") Long id);
}