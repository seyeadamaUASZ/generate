
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Ouverturecompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface OuverturecompteRepository extends JpaRepository<Ouverturecompte, Long> {
	 @Query("SELECT s from Ouverturecompte s WHERE s.status IN (SELECT t.tskStatusId from Task t WHERE t.poOwner.proId=:poOwner )")
	    Iterable<Ouverturecompte> listOuverturecompte(@Param("poOwner") Long poOwner);
	 
	 @Query("SELECT s from Ouverturecompte s WHERE s.owner =:owner ")
	    Iterable<Ouverturecompte> listOuverturecompteById(@Param("owner") Long owner);
 @Query("SELECT s from Ouverturecompte s WHERE s.id =:id ")
	   Ouverturecompte getOneOuverturecompte(@Param("id") Long id);

@Query("SELECT s from Ouverturecompte s WHERE s.id IN (SELECT t.idLink from Assistantclient t where t.poOwner=:poOwner)")
Iterable<Ouverturecompte> listOuverturecompteTraitesAssistantclient(@Param("poOwner") Long poOwner);

@Query("SELECT s from Ouverturecompte s WHERE s.id IN (SELECT t.idLink from Gestionnairecompte t where t.poOwner=:poOwner)")
Iterable<Ouverturecompte> listOuverturecompteTraitesGestionnairecompte(@Param("poOwner") Long poOwner);

}