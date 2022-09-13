/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.gainde2000.pi.core.entity.QrCode;

/**
 *
 * @author asow
 */
public interface QrCodeRepository extends JpaRepository<QrCode, Long>{
     public QrCode findByQrcId(Long qrcodeid);
    
}
