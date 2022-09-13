
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Validation;
import sn.gainde2000.pi.core.service.IValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.ValidationRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ValidationServiceImpl implements IValidationService {

    @Autowired
    private ValidationRepository validationRepository;

    @Override
    public List<Validation> findAll() {

        return validationRepository.findAll();
    }
    @Override
    public Validation save(Validation validation) {

        return validationRepository.save(validation);
    }
    @Override
    public void delete(Validation validation) {

  validationRepository.delete(validation);
    }
	@Override
	public Optional<Validation> findById(Long id) {
		return validationRepository.findById(id);
	}
@Override
	public Validation getOneValidation(Long id) {
		// TODO Auto-generated method stub
		return validationRepository.getOneValidation(id);
	}
@Override
public Iterable<Validation> listValidation(Long poOwner) {
    return validationRepository.listValidation(poOwner);
}
@Override
public Iterable<Validation> listValidationById(Long owner) {
    return validationRepository.listValidationById(owner);
}}