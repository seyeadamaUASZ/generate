
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

}