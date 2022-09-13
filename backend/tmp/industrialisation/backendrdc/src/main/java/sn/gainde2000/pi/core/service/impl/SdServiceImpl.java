
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Sd;
import sn.gainde2000.pi.core.service.ISdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.SdRepository;
import java.util.List;

@Service
public class SdServiceImpl implements ISdService {

    @Autowired
    private SdRepository sdRepository;

    @Override
    public List<Sd> findAll() {

        return sdRepository.findAll();
    }
    @Override
    public Sd save(Sd sd) {

        return sdRepository.save(sd);
    }
    @Override
    public void delete(Sd sd) {

  sdRepository.delete(sd);
    }
}