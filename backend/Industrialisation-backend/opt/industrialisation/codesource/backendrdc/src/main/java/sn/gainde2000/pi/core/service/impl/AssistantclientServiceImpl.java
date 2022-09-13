
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Assistantclient;
import sn.gainde2000.pi.core.service.IAssistantclientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.AssistantclientRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AssistantclientServiceImpl implements IAssistantclientService {

    @Autowired
    private AssistantclientRepository assistantclientRepository;

    @Override
    public List<Assistantclient> findAll() {

        return assistantclientRepository.findAll();
    }
    @Override
    public Assistantclient save(Assistantclient assistantclient) {

        return assistantclientRepository.save(assistantclient);
    }
    @Override
    public void delete(Assistantclient assistantclient) {

  assistantclientRepository.delete(assistantclient);
    }
	@Override
	public Optional<Assistantclient> findById(Long id) {
		return assistantclientRepository.findById(id);
	}@Override
	public Assistantclient getOneAssistantclient(Long id) {
		return assistantclientRepository.getOneAssistantclient(id);
	}
@Override
public Iterable<Assistantclient> listAssistantclient(Long poOwner) {
    return assistantclientRepository.listAssistantclient(poOwner);
}
@Override
public Iterable<Assistantclient> listAssistantclientById(Long owner) {
    return assistantclientRepository.listAssistantclientById(owner);
}}