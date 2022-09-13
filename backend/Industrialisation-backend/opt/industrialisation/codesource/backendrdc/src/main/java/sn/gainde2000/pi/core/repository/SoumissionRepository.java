
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Soumission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface SoumissionRepository extends JpaRepository<Soumission, Long> {
	 @Query("SELECT s from Soumission s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner=:poOwner )")
	    Iterable<Soumission> listSoumission(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from Soumission s WHERE s.owner =:owner ")
	    Iterable<Soumission> listSoumissionById(@Param("owner") Long owner);
 @Query("SELECT s from Soumission s WHERE s.id =:id ")
	   Soumission getOneSoumission(@Param("id") Long id);
}