package sn.gainde2000.pi.core.service;

import java.util.List;
import java.util.Optional;

import sn.gainde2000.pi.core.entity.Profil;
import sn.gainde2000.pi.core.entity.Utilisateur;

public interface IUtilisateur {

    public void addUtilisateur(Utilisateur utilisateur);

    public List<Utilisateur> listUtilisateur();

    public Utilisateur getUtilisateur(Long id);

    public void deleteUtilisateur(Utilisateur utilisateur);

    public void updateUtilsateur(Utilisateur utilisateur);

    public Utilisateur findByUtiId(String userid);

    public Utilisateur findByUtiUsername(String username);

    public int nombreUtilisateur();

    public Optional<Utilisateur> findById(Long id);

    public int nombreUtilisateurByProfil(String libelle);

    public Utilisateur verifyLangueEtTheme(String param_uti_username);

    public Utilisateur findByUtiEmail(String email);

    public Utilisateur listByUtid(Long id);

    public int findUsersByUtiEmail(String email);

    public int findUsersByUtiUsername(String username);

    public List<Utilisateur> findUsersByUtiProfil(Profil profil);
    public int verifierTempsSession(String username);
}
