
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Demande;
import java.util.List;
import java.util.Optional;

public interface IDemandeService {

Optional<Demande> findById(Long id);
List<Demande> findAll();
Demande save(Demande demande);
void delete(Demande demande);
}