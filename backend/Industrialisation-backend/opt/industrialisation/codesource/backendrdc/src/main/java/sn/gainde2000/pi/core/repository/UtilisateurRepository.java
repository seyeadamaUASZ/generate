/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sn.gainde2000.pi.core.entity.Action;
import sn.gainde2000.pi.core.entity.Profil;
import sn.gainde2000.pi.core.entity.Utilisateur;

/**
 *
 * @author yougadieng
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByUtiId(String userid);

    Utilisateur findByUtiUsername(String username);

    Utilisateur findByUtiEmail(String email);

    @Query("SELECT COUNT(*) FROM Utilisateur p where p.utiEmail=:email")
    int findUsersByUtiEmail(String email);

    @Query("SELECT COUNT(*) FROM Utilisateur p where p.utiUsername=:username")
    int findUsersByUtiUsername(String username);

    @Query("SELECT COUNT(*) FROM Utilisateur p")
    int nombreUtilisateur();

    @Query("select u from Utilisateur u where u.utiId =:id")
    public Utilisateur listByUtid(@Param("id") Long id);

    @Query("SELECT COUNT(*) FROM Utilisateur p,Profil pro where p.uti_pro_id=pro.proId and pro.proLibelle like %:libelle%")
    int nombreUtilisateurByProfil(@Param("libelle") String libelle);

    @Query(value = "select * FROM td_utilisateur where uti_username=:param_uti_username", nativeQuery = true)
    Utilisateur verifyLangueEtTheme(@Param("param_uti_username") String param_uti_username);

    @Query("SELECT u FROM Utilisateur u  WHERE u.uti_pro_id =:profil")
    List<Utilisateur> findUsersByUtiProfil(@Param("profil") Profil profil);
    
     @Query(value ="SELECT ROUND(( UNIX_TIMESTAMP(uti_temps_session) - (UNIX_TIMESTAMP())) / 60)  FROM td_utilisateur where uti_username=:username ",nativeQuery = true)
     public int verifSession(@Param("username") String username);
    

   

}
