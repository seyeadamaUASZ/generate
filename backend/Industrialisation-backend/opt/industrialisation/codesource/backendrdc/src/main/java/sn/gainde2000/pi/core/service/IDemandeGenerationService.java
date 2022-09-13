
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.DemandeGeneration;
import java.util.List;
import java.util.Optional;

public interface IDemandeGenerationService {

Optional<DemandeGeneration> findById(Long id);
List<DemandeGeneration> findAll();
DemandeGeneration save(DemandeGeneration demandegeneration);
void delete(DemandeGeneration demandegeneration);
Iterable<DemandeGeneration> listDemandeGeneration(Long poOwner);
Iterable<DemandeGeneration> listDemandeGenerationById(Long owner);
DemandeGeneration getOneDemandeGeneration(Long id);}