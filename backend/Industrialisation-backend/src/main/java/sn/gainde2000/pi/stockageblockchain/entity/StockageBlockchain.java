package sn.gainde2000.pi.stockageblockchain.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import sn.gainde2000.pi.core.entity.Utilisateur;

/**
 *
 * @author pbf
 */
@Entity
@Table(name = "td_stockage_blockchain", schema = "")
public class StockageBlockchain {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@NotNull
	@Column(name = "stbl_id")
	private Long stblId;
	
	@Column(name = "stbl_type")
	private String stblType;
	
	@Column(name = "stbl_blob", columnDefinition = "LONGBLOB")
	private byte[] stblBlob;
	
	@Size(max = 255)
    @Column(name = "stbl_nom")
    private String stblNom;
	
	@Size(max = 255)
    @Column(name = "stbl_description")
    private String stblDescription;
	
	@Size(max = 255)
    @Column(name = "stbl_hash",unique = true)
    private String stblHash;
	
    @Column(name = "stbl_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stblDate;
    
	@ManyToOne(targetEntity = Utilisateur.class)
	@JoinColumn(name = "stbl_user_id", referencedColumnName = "uti_id", insertable = true, updatable = true)
	private Utilisateur utilisateur;

	public Long getStblId() {
		return stblId;
	}

	public void setStblId(Long stblId) {
		this.stblId = stblId;
	}

	public String getStblType() {
		return stblType;
	}

	public void setStblType(String stblType) {
		this.stblType = stblType;
	}

	public byte[] getStblBlob() {
		return stblBlob;
	}

	public void setStblBlob(byte[] stblBlob) {
		this.stblBlob = stblBlob;
	}

	public String getStblNom() {
		return stblNom;
	}

	public void setStblNom(String stblNom) {
		this.stblNom = stblNom;
	}

	public String getStblDescription() {
		return stblDescription;
	}

	public void setStblDescription(String stblDescription) {
		this.stblDescription = stblDescription;
	}

	public String getStblHash() {
		return stblHash;
	}

	public void setStblHash(String stblHash) {
		this.stblHash = stblHash;
	}

	public Date getStblDate() {
		return stblDate;
	}

	public void setStblDate(Date stblDate) {
		this.stblDate = stblDate;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	

}
