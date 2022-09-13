/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.entity;

import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author asow
 */
@Entity
@Table(name = "td_qrcode")
public class QrCode {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @NotNull
    @Column(name = "qrc_id")
    private Long qrcId;
    @Size(max = 20)
    @Column(name = "qrc_nom")
    private String qrcNom;
    @Size(max = 255)
    @Column(name = "qrc_couleur")
    private String qrcCouleur;
    @Size(max = 255)
    @Column(name = "qrc_forme")
    private String qrcForme;
    @Size(max = 255)
    @Column(name = "qrc_contenu")
    private String qrcContenu;
    @Lob
    @Column(name = "qrc_qrcodeByte", length = 10000000, columnDefinition = "LONGBLOB")
    public byte[] qrcQrcodeByte;
    @OneToMany(mappedBy = "rptQrcId")
    private Set<Rapport> rapport;

    public QrCode() {
    }

    public QrCode(Long qrcId) {
        this.qrcId = qrcId;
    }

    public QrCode(String qrcNom, String qrcCouleur, String qrcForme, String qrcContenu, byte[] qrcQrcodeByte) {
        this.qrcNom = qrcNom;
        this.qrcCouleur = qrcCouleur;
        this.qrcForme = qrcForme;
        this.qrcContenu = qrcContenu;
        this.qrcQrcodeByte = qrcQrcodeByte;
    }

    public QrCode(String originalFilename, String contentType, byte[] compressBytes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getQrcId() {
        return qrcId;
    }

    public void setQrcId(Long qrcId) {
        this.qrcId = qrcId;
    }

    public String getQrcNom() {
        return qrcNom;
    }

    public void setQrcNom(String qrcNom) {
        this.qrcNom = qrcNom;
    }

    public String getQrcCouleur() {
        return qrcCouleur;
    }

    public void setQrcCouleur(String qrcCouleur) {
        this.qrcCouleur = qrcCouleur;
    }

    public String getQrcForme() {
        return qrcForme;
    }

    public void setQrcForme(String qrcForme) {
        this.qrcForme = qrcForme;
    }

    public String getQrcContenu() {
        return qrcContenu;
    }

    public void setQrcContenu(String qrcContenu) {
        this.qrcContenu = qrcContenu;
    }

    public byte[] getQrcQrcodeByte() {
        return qrcQrcodeByte;
    }

    public void setQrcQrcodeByte(byte[] qrcQrcodeByte) {
        this.qrcQrcodeByte = qrcQrcodeByte;
    }

    @Override
    public String toString() {
        return "QrCode{" + "qrcId=" + qrcId + '}';
    }

    public Image get() {
        // TODO Auto-generated method stub
        return null;
    }

}
