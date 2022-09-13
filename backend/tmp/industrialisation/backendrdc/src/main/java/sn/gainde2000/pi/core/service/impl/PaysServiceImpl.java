package sn.gainde2000.pi.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.entity.Pays;
import sn.gainde2000.pi.core.repository.PaysRepository;
import sn.gainde2000.pi.core.service.IpaysService;

import java.util.List;
import java.util.Optional;

@Service
public class PaysServiceImpl implements IpaysService {
    @Autowired
    PaysRepository paysRepository;
    @Override
    public List<Pays> getListPays() {
        return paysRepository.findAll();
    }

    @Override
    public Optional<Pays> findByPaysId(Long id) {
        return paysRepository.findById(id);
    }


    @Override
    public Pays save(Pays p) {
        return paysRepository.save(p);
    }

    @Override
    public void delete(Pays p) {
        paysRepository.delete(p);
    }
}
