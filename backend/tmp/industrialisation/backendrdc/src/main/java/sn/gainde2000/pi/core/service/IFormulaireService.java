package sn.gainde2000.pi.core.service;

import java.util.List;
import java.util.Optional;

import sn.gainde2000.pi.core.entity.Formulaire;

public interface IFormulaireService {

    Formulaire findByfrmId(Long frmId);
    Formulaire findByfrmNom(String frmNom);
    List<Formulaire> findByfrmAppId(Long id);

    List<Formulaire> getAllFormulaireLibreValider();

    List<Formulaire> listformnotgenerate();

    List<Formulaire> listformgenerate();

    List<Formulaire> getAllFormulaire();

    List<Formulaire> listformByAppId(Long id);

    List<Formulaire> listformByAppIdOulibre(Long id);

    Formulaire save(Formulaire formulaire);

    Optional<Formulaire> findById(Long id);

    void delete(Formulaire formulaire);

    // List<Formulaire> getAllFormulaireLibre();
    public void lierFormulaire(String idApp, String idFormulaire);

    public void enleverLiasonFormulaire(String idFormulaire);
    // List<Formulaire> getAllFormulaireLibre();
    public int NombreFormulaire();


}
