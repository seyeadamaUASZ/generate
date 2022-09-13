
package sn.gainde2000.pi.core.repository;

import sn.gainde2000.pi.core.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

}