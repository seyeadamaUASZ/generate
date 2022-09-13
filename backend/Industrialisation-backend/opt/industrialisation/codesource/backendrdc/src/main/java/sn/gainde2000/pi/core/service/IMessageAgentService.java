
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.MessageAgent;
import java.util.List;
import java.util.Optional;

public interface IMessageAgentService {

Optional<MessageAgent> findById(Long id);
List<MessageAgent> findAll();
MessageAgent save(MessageAgent messageagent);
void delete(MessageAgent messageagent);
Iterable<MessageAgent> listMessageAgent(Long poOwner);
Iterable<MessageAgent> listMessageAgentById(Long owner);
MessageAgent getOneMessageAgent(Long id);}