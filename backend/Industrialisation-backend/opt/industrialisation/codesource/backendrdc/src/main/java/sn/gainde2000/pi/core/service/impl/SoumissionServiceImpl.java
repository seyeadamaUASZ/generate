
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Soumission;
import sn.gainde2000.pi.core.service.ISoumissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.SoumissionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SoumissionServiceImpl implements ISoumissionService {

    @Autowired
    private SoumissionRepository soumissionRepository;

    @Override
    public List<Soumission> findAll() {

        return soumissionRepository.findAll();
    }
    @Override
    public Soumission save(Soumission soumission) {

        return soumissionRepository.save(soumission);
    }
    @Override
    public void delete(Soumission soumission) {

  soumissionRepository.delete(soumission);
    }
	@Override
	public Optional<Soumission> findById(Long id) {
		return soumissionRepository.findById(id);
	}
@Override
	public Soumission getOneSoumission(Long id) {
		// TODO Auto-generated method stub
		return soumissionRepository.getOneSoumission(id);
	}
@Override
public Iterable<Soumission> listSoumission(Long poOwner) {
    return soumissionRepository.listSoumission(poOwner);
}
@Override
public Iterable<Soumission> listSoumissionById(Long owner) {
    return soumissionRepository.listSoumissionById(owner);
}}