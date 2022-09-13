
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Assistantclient;
import java.util.List;
import java.util.Optional;

public interface IAssistantclientService {

Optional<Assistantclient> findById(Long id);
List<Assistantclient> findAll();
Assistantclient save(Assistantclient assistantclient);
void delete(Assistantclient assistantclient);
Iterable<Assistantclient> listAssistantclient(Long poOwner);
Iterable<Assistantclient> listAssistantclientById(Long owner);
Assistantclient getOneAssistantclient(Long id);
}