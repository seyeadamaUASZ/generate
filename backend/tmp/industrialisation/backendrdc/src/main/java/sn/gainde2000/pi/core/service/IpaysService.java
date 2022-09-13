package sn.gainde2000.pi.core.service;

import sn.gainde2000.pi.core.entity.Pays;

import java.util.List;
import java.util.Optional;

public interface IpaysService {
    public List<Pays> getListPays();
    public Optional<Pays> findByPaysId(Long id);
    public Pays save(Pays p);
    public void delete(Pays p);
}
