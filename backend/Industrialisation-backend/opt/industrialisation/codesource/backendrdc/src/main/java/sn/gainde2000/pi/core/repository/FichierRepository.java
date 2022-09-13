/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gainde2000.pi.core.entity.Fichier;

/**
 *
 * @author Administrateur_Aly
 */
@Repository
public interface FichierRepository extends JpaRepository<Fichier, Long> {
	
	 Fichier  findByfhrNom(String fhrNom);

     @Query("SELECT f FROM Fichier f where f.fhrAppId = null")
     List<Fichier> getFichierByApp();
     @Query("SELECT f FROM Fichier f where f.fhrAppId =:id")
     List<Fichier> getFichierByAppId(@Param("id")Long id);
     @Query("SELECT f FROM Fichier f where f.fhrAppId =:id or f.fhrAppId=null")
     List<Fichier> getFichierByAppIdOuLibre(@Param("id")Long id);

     @Modifying
     @Transactional
     @Query(value = "update td_fichier set fhr_app_id =:idApp where fhr_id=:idFichier", nativeQuery = true)
     public void lierFichier(@Param("idApp") String idApp, @Param("idFichier") String idFichier);

     @Modifying
     @Transactional
     @Query(value = "update td_fichier set fhr_app_id =null where fhr_id=:idFichier", nativeQuery = true)
     public void enleverLiaisonFichier(@Param("idFichier") String idFichier);
}
