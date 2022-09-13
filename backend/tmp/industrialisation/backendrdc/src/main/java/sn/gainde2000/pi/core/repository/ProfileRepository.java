/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.gainde2000.pi.core.entity.Profil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yougadieng
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profil, Long> {

  public  Profil findByProLibelle(String profile);
  public  Profil findByProId(String proId);
}
