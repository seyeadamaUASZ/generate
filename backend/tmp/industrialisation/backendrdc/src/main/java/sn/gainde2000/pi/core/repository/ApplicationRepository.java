package sn.gainde2000.pi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sn.gainde2000.pi.core.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

     // Champs findByappId(Long appId);
     Application findByappId(Long appId);

     Application findByAppEmail(String email);

     Application findByAppNom(String appNom);

     //Application findByAppEmail(String email);

     @Query("SELECT COUNT(*) FROM Application p")
     int nombreAplication();

     @Modifying
     @Transactional
     @Query(value = "update td_application set app_etape_creation=:etape  where app_id=:idApp", nativeQuery = true)
     void updateEtapeCreation(@Param("etape") String etape, @Param("idApp") String idApp);

     @Modifying
     @Transactional
     @Query(value = "update tp_workflow set wkf_app_id=null where wkf_app_id=:idApp", nativeQuery = true)
     void updateLiaisonWorkflow(@Param("idApp") Long idApp);

    @Modifying
    @Transactional
    @Query(value = "update tp_formulaire set frm_app_id=null where frm_app_id=:idApp", nativeQuery = true)

     void updateLiaisonFormulaire(@Param("idApp") Long idApp);

     @Modifying
     @Transactional
     @Query(value = "update tp_rapport set rpt_app_id=null  where rpt_app_id=:idApp", nativeQuery = true)
     void updateLiaisonFichier(@Param("idApp") Long idApp);
     @Modifying
     @Transactional
     @Query(value = "delete from tr_app_fonc  where appli_fonc_app_id=:idApp", nativeQuery = true)
     void updateLiaisonTableLiaison(@Param("idApp") Long idApp);
     /* @Transactional
=======
public interface ApplicationRepository extends JpaRepository<Application, Long>{
	Application findByappId(Long appId);
     Application findByAppEmail(String email);
     Application findByAppId(Long appId);
	    @Query("SELECT COUNT(*) FROM Application p")
	     int  nombreAplication();
         /* @Transactional
>>>>>>> e22e2449889033b33e304fa5287ea176c802fe6a
          @Modifying
          @Query(value="")
          void update(@Param("email")String email);*/
}
