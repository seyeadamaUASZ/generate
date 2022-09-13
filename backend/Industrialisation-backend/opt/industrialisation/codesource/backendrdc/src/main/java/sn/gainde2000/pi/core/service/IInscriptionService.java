
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Inscription;
import java.util.List;
import java.util.Optional;

public interface IInscriptionService {

Optional<Inscription> findById(Long id);
List<Inscription> findAll();
Inscription save(Inscription inscription);
void delete(Inscription inscription);
}