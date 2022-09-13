
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {
	 @Query("SELECT s from Verification s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner=:poOwner )")
	    Iterable<Verification> listVerification(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from Verification s WHERE s.owner =:owner ")
	    Iterable<Verification> listVerificationById(@Param("owner") Long owner);
 @Query("SELECT s from Verification s WHERE s.id =:id ")
	   Verification getOneVerification(@Param("id") Long id);
}