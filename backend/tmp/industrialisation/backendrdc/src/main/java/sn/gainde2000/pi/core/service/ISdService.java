
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Sd;
import java.util.List;

public interface ISdService {

List<Sd> findAll();
Sd save(Sd sd);
void delete(Sd sd);
}