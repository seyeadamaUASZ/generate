/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.paiement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.pi.paiement.entity.OperateurPaiement;

/**
 *
 * @author yougadieng
 */
@Repository
public interface OperateurPaiementRepository extends JpaRepository<OperateurPaiement, Long> {

    @Modifying
    @Transactional
    @Query(value = "update tp_operateur_paiement set opa_is_active=true where opa_id=:idOpa", nativeQuery = true)
    void activerOperateur(@Param("idOpa") Long idOpa);

    @Modifying
    @Transactional
    @Query(value = "update tp_operateur_paiement set opa_is_active=false where opa_id=:idOpa", nativeQuery = true)
    void desactiverOperateur(@Param("idOpa") Long idOpa);
}
