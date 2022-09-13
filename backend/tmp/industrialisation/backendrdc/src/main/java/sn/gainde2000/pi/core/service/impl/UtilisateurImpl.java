package sn.gainde2000.pi.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.gainde2000.pi.core.entity.Profil;
import sn.gainde2000.pi.core.entity.Utilisateur;
import sn.gainde2000.pi.core.repository.UtilisateurRepository;
import sn.gainde2000.pi.core.service.IUtilisateur;

@Service
public class UtilisateurImpl implements IUtilisateur{
 
	 @Autowired 
	    UtilisateurRepository userRepository;
	
	@Override
	public void addUtilisateur(Utilisateur utilisateur) {
		this.userRepository.save(utilisateur);
		
	}

	@Override
	public List<Utilisateur> listUtilisateur() {
		
		return this.userRepository.findAll();
	}

	@Override
	public Utilisateur getUtilisateur(Long id) {

		return this.userRepository.listByUtid(id);
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) {
		
		this.userRepository.delete(utilisateur);
	}

	@Override
	public void updateUtilsateur(Utilisateur utilisateur) {
		this.userRepository.save(utilisateur);
	}

	@Override
	public Utilisateur findByUtiId(String userid) {
	return this.userRepository.findByUtiId(userid);
	}

	@Override
	public Utilisateur findByUtiUsername(String username) {
		
		return this.userRepository.findByUtiUsername(username);
	}

	@Override
	public Utilisateur findByUtiEmail(String email) {
		
		return this.userRepository.findByUtiEmail(email);
	}

	@Override
	public int nombreUtilisateur() {
		
		return this.userRepository.nombreUtilisateur();
	}

	
	@Override
	public int nombreUtilisateurByProfil(String libelle) {
		
		return this.userRepository.nombreUtilisateurByProfil(libelle);
	}

	@Override
	public Optional<Utilisateur> findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public Utilisateur verifyLangueEtTheme(String param_uti_username) {
		// TODO Auto-generated method stub
		return userRepository.verifyLangueEtTheme(param_uti_username);
	}

	@Override
	public Utilisateur listByUtid(Long id) {
		// TODO Auto-generated method stub
		return userRepository.listByUtid(id);
	}

	@Override
	public int findUsersByUtiEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findUsersByUtiEmail(email);
	}

	@Override
	public int findUsersByUtiUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findUsersByUtiUsername(username);
	}

	@Override
	public List<Utilisateur> findUsersByUtiProfil(Profil profil) {
		return userRepository.findUsersByUtiProfil(profil);
	}

	

	
}
