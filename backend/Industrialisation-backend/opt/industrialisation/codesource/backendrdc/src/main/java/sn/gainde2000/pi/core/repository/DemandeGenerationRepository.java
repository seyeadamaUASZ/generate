
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.DemandeGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface DemandeGenerationRepository extends JpaRepository<DemandeGeneration, Long> {
	 @Query("SELECT s from DemandeGeneration s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner.proId=:poOwner )")
	    Iterable<DemandeGeneration> listDemandeGeneration(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from DemandeGeneration s WHERE s.owner =:owner ")
	    Iterable<DemandeGeneration> listDemandeGenerationById(@Param("owner") Long owner);
 @Query("SELECT s from DemandeGeneration s WHERE s.id =:id ")
	   DemandeGeneration getOneDemandeGeneration(@Param("id") Long id);

}