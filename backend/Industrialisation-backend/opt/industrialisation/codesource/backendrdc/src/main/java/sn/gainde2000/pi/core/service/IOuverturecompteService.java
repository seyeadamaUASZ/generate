
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Ouverturecompte;
import java.util.List;
import java.util.Optional;

public interface IOuverturecompteService {

Optional<Ouverturecompte> findById(Long id);
List<Ouverturecompte> findAll();
Ouverturecompte save(Ouverturecompte ouverturecompte);
void delete(Ouverturecompte ouverturecompte);
Iterable<Ouverturecompte> listOuverturecompte(Long poOwner);
Iterable<Ouverturecompte> listOuverturecompteById(Long owner);
Ouverturecompte getOneOuverturecompte(Long id);
Iterable<Ouverturecompte> listOuverturecompteTraitesAssistantclient(Long poOwner);
Iterable<Ouverturecompte> listOuverturecompteTraitesGestionnairecompte(Long poOwner);
}