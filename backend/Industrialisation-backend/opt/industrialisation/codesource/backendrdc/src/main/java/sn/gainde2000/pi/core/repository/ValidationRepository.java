
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long> {
	 @Query("SELECT s from Validation s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner=:poOwner )")
	    Iterable<Validation> listValidation(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from Validation s WHERE s.owner =:owner ")
	    Iterable<Validation> listValidationById(@Param("owner") Long owner);
 @Query("SELECT s from Validation s WHERE s.id =:id ")
	   Validation getOneValidation(@Param("id") Long id);
}