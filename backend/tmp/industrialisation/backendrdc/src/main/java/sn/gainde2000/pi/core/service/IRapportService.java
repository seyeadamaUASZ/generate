/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.service;

import java.util.List;

import sn.gainde2000.pi.core.entity.Menu;
import sn.gainde2000.pi.core.entity.Rapport;

/**
 *
 * @author asow
 */
public interface IRapportService {

    public Rapport save(Rapport rapport);

    public Rapport findById(Long id);

    public List<Rapport> listrapportnotgenerate();

    public List<Rapport> getlistrapportgenerate();

    public List<Rapport> getlistrapportgenerateEtLibre();
    public List<Rapport> getlistrapportgenerateEtLibreOuSpecifique(Long id);

    void delete(Rapport rapport);

    public void lierFichier(String idApp, String idFichier);

    public void enleverLiasonFichier(String idFichier);

    public List<Rapport> getAllFichierByIdApp(Long id);
    
    public List<Rapport> getAllRapportByMenu(Menu menu);
    
    public int nombreFichier();


}
