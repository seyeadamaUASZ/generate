
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Verification;
import sn.gainde2000.pi.core.service.IVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.VerificationRepository;
import java.util.List;
import java.util.Optional;

@Service
public class VerificationServiceImpl implements IVerificationService {

    @Autowired
    private VerificationRepository verificationRepository;

    @Override
    public List<Verification> findAll() {

        return verificationRepository.findAll();
    }
    @Override
    public Verification save(Verification verification) {

        return verificationRepository.save(verification);
    }
    @Override
    public void delete(Verification verification) {

  verificationRepository.delete(verification);
    }
	@Override
	public Optional<Verification> findById(Long id) {
		return verificationRepository.findById(id);
	}
@Override
	public Verification getOneVerification(Long id) {
		// TODO Auto-generated method stub
		return verificationRepository.getOneVerification(id);
	}
@Override
public Iterable<Verification> listVerification(Long poOwner) {
    return verificationRepository.listVerification(poOwner);
}
@Override
public Iterable<Verification> listVerificationById(Long owner) {
    return verificationRepository.listVerificationById(owner);
}}