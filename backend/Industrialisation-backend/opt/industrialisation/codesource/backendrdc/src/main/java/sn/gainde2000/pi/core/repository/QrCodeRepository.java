/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.gainde2000.pi.core.entity.QrCode;

/**
 *
 * @author asow
 */
public interface QrCodeRepository extends JpaRepository<QrCode, Long> {

    public QrCode findByQrcId(Long qrcodeid);

    @Query("SELECT q FROM QrCode q where q.qrcValider= 'Modeliser'")
    List<QrCode> listQrcodeNotModeliser();

    @Query("SELECT q FROM QrCode q where q.qrcValider = 'Valider'")
    List<QrCode> listQrcodeModeliser();

}
