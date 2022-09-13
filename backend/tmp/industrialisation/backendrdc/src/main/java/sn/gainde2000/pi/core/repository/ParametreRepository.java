/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.gainde2000.pi.core.entity.Parametre;
import sn.gainde2000.pi.core.entity.Utilisateur;

/**
 *
 * @author yougadieng
 */
@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Long> {

    Parametre findByParamUtiUsername(String param_uti_username);

    @Query(value = "select count(*) from tp_parametre where param_uti_username=:param_uti_username", nativeQuery = true)
    int verifyParametre(@Param("param_uti_username") String param_uti_username);

   

    @Query(value = "SELECT * FROM tp_parametre order by param_id DESC LIMIT 1", nativeQuery = true)
    Parametre selectParam();

}
