
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Assistantclient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AssistantclientRepository extends JpaRepository<Assistantclient, Long> {
	 @Query("SELECT s from Assistantclient s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner.proId=:poOwner )")
	    Iterable<Assistantclient> listAssistantclient(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from Assistantclient s WHERE s.owner =:owner ")
	    Iterable<Assistantclient> listAssistantclientById(@Param("owner") Long owner);
 @Query("SELECT s from Assistantclient s WHERE s.id =:id ")
	   Assistantclient getOneAssistantclient(@Param("id") Long id);

}