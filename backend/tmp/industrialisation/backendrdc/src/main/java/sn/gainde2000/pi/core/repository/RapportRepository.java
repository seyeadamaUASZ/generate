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
import org.springframework.transaction.annotation.Transactional;

import sn.gainde2000.pi.core.entity.Menu;
import sn.gainde2000.pi.core.entity.Rapport;

/**
 *
 * @author asow
 */
public interface RapportRepository extends JpaRepository<Rapport, Long> {

    Rapport findByrptId(Long rptId);

    @Query("SELECT r FROM Rapport r where r.rptValider= 'Modeliser'")
    List<Rapport> listrapportnotgenerate();

    @Query("SELECT r FROM Rapport r where r.rptValider = 'Valider' and r.rptAppId=null")
    List<Rapport> listrapportgenerateLibre();
    @Query("SELECT r FROM Rapport r where r.rptValider = 'Valider' and r.rptAppId=:id or r.rptAppId=null")
    List<Rapport> listrapportgenerateLibreOuSpecifique(@Param("id") Long id);

    @Query("SELECT r FROM Rapport r where r.rptValider = 'Valider'")
    List<Rapport> listrapportgenerate();
    
    @Query("SELECT r FROM Rapport r LEFT JOIN MenuRapport mr ON (mr.rapport.rptId=r.rptId) WHERE mr.menu=:menu")
    List<Rapport> getAllRapportByMenu(@Param("menu") Menu menu);
    
    @Query("SELECT count(*) FROM Rapport r where r.rptValider = 'Valider'")
    public int  nombreRapportGenerate();

    @Query(value = "select *from tp_rapport where rpt_app_id=:id", nativeQuery = true)
    List<Rapport> getFichierByAppId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update tp_rapport set rpt_app_id =:idApp where rpt_id=:idFichier", nativeQuery = true)
    public void LierFicherEtApp(@Param("idApp") String idApp, @Param("idFichier") String idFichier);

    @Modifying
    @Transactional
    @Query(value = "update tp_rapport set rpt_app_id =null where rpt_id=:idFichier", nativeQuery = true)
    public void enleverLiaisonFichier(@Param("idFichier") String idFichier);

}
