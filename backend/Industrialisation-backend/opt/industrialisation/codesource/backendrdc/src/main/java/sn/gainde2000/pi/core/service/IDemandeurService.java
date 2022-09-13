
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Demandeur;
import java.util.List;
import java.util.Optional;

public interface IDemandeurService {

Optional<Demandeur> findById(Long id);
List<Demandeur> findAll();
Demandeur save(Demandeur demandeur);
void delete(Demandeur demandeur);
Iterable<Demandeur> listDemandeur(Long poOwner);
Iterable<Demandeur> listDemandeurById(Long owner);
Demandeur getOneDemandeur(Long id);}