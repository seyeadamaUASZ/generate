
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Verification;
import java.util.List;
import java.util.Optional;

public interface IVerificationService {

Optional<Verification> findById(Long id);
List<Verification> findAll();
Verification save(Verification verification);
void delete(Verification verification);
Iterable<Verification> listVerification(Long poOwner);
Iterable<Verification> listVerificationById(Long owner);
Verification getOneVerification(Long id);}