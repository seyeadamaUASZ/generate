
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Traitement;
import java.util.List;
import java.util.Optional;

public interface ITraitementService {

Optional<Traitement> findById(Long id);
List<Traitement> findAll();
Traitement save(Traitement traitement);
void delete(Traitement traitement);
Iterable<Traitement> listTraitement(Long poOwner);
Iterable<Traitement> listTraitementById(Long owner);
Traitement getOneTraitement(Long id);}