
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.MessageAgent;
import sn.gainde2000.pi.core.service.IMessageAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.MessageAgentRepository;
import java.util.List;
import java.util.Optional;

@Service
public class MessageAgentServiceImpl implements IMessageAgentService {

    @Autowired
    private MessageAgentRepository messageagentRepository;

    @Override
    public List<MessageAgent> findAll() {

        return messageagentRepository.findAll();
    }
    @Override
    public MessageAgent save(MessageAgent messageagent) {

        return messageagentRepository.save(messageagent);
    }
    @Override
    public void delete(MessageAgent messageagent) {

  messageagentRepository.delete(messageagent);
    }
	@Override
	public Optional<MessageAgent> findById(Long id) {
		return messageagentRepository.findById(id);
	}@Override
	public MessageAgent getOneMessageAgent(Long id) {
		return messageagentRepository.getOneMessageAgent(id);
	}
@Override
public Iterable<MessageAgent> listMessageAgent(Long poOwner) {
    return messageagentRepository.listMessageAgent(poOwner);
}
@Override
public Iterable<MessageAgent> listMessageAgentById(Long owner) {
    return messageagentRepository.listMessageAgentById(owner);
}}