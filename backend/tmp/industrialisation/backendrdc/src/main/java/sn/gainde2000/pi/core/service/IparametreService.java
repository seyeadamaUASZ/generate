package sn.gainde2000.pi.core.service;

import sn.gainde2000.pi.core.entity.Parametre;

import java.util.List;
import java.util.Optional;

public interface IparametreService {
    public List<Parametre> getListParam();
    public Optional<Parametre> findByParamId(Long id);
    public Parametre selectParam();
    public Parametre save(Parametre p);
    public void delete(Parametre p);
}
