package sn.gainde2000.pi.capteurs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "td_donneecapteur", schema = "")
public class DonneeRecupereeCapteur implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDonnees;
	private double data1;
	private double data2;
	private Date createdAt=new Date();
	@ManyToOne
	@JoinColumn(name="capteur")
	private Capteur capteur;
	
	
	
	
	public Capteur getCapteur() {
		return capteur;
	}


	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
	}


	public DonneeRecupereeCapteur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DonneeRecupereeCapteur(double data1, double data2) {
		super();
		this.data1 = data1;
		this.data2 = data2;
	}


	public Long getIdDonnees() {
		return idDonnees;
	}
	public void setIdDonnees(Long idDonnees) {
		this.idDonnees = idDonnees;
	}
	public double getData1() {
		return data1;
	}
	public void setData1(double data1) {
		this.data1 = data1;
	}
	public double getData2() {
		return data2;
	}
	public void setData2(double data2) {
		this.data2 = data2;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
