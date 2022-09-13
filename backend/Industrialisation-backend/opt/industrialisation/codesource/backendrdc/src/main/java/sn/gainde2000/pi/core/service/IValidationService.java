
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Validation;
import java.util.List;
import java.util.Optional;

public interface IValidationService {

Optional<Validation> findById(Long id);
List<Validation> findAll();
Validation save(Validation validation);
void delete(Validation validation);
Iterable<Validation> listValidation(Long poOwner);
Iterable<Validation> listValidationById(Long owner);
Validation getOneValidation(Long id);}