
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Gestionnairecompte;
import java.util.List;
import java.util.Optional;

public interface IGestionnairecompteService {

Optional<Gestionnairecompte> findById(Long id);
List<Gestionnairecompte> findAll();
Gestionnairecompte save(Gestionnairecompte gestionnairecompte);
void delete(Gestionnairecompte gestionnairecompte);
Iterable<Gestionnairecompte> listGestionnairecompte(Long poOwner);
Iterable<Gestionnairecompte> listGestionnairecompteById(Long owner);
Gestionnairecompte getOneGestionnairecompte(Long id);
}