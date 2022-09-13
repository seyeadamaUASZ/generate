
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.MessageAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MessageAgentRepository extends JpaRepository<MessageAgent, Long> {
	 @Query("SELECT s from MessageAgent s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner.proId=:poOwner )")
	    Iterable<MessageAgent> listMessageAgent(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from MessageAgent s WHERE s.owner =:owner ")
	    Iterable<MessageAgent> listMessageAgentById(@Param("owner") Long owner);
 @Query("SELECT s from MessageAgent s WHERE s.id =:id ")
	   MessageAgent getOneMessageAgent(@Param("id") Long id);

}