
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Soumission;
import java.util.List;
import java.util.Optional;

public interface ISoumissionService {

Optional<Soumission> findById(Long id);
List<Soumission> findAll();
Soumission save(Soumission soumission);
void delete(Soumission soumission);
Iterable<Soumission> listSoumission(Long poOwner);
Iterable<Soumission> listSoumissionById(Long owner);
Soumission getOneSoumission(Long id);}