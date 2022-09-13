
package sn.gainde2000.pi.core.service.impl;

import sn.gainde2000.pi.core.entity.Test1;
import sn.gainde2000.pi.core.service.ITest1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gainde2000.pi.core.repository.Test1Repository;
import java.util.List;
import java.util.Optional;

@Service
public class Test1ServiceImpl implements ITest1Service {

    @Autowired
    private Test1Repository test1Repository;

    @Override
    public List<Test1> findAll() {

        return test1Repository.findAll();
    }
    @Override
    public Test1 save(Test1 test1) {

        return test1Repository.save(test1);
    }
    @Override
    public void delete(Test1 test1) {

  test1Repository.delete(test1);
    }
	@Override
	public Optional<Test1> findById(Long id) {
		return test1Repository.findById(id);
	}@Override
	public Test1 getOneTest1(Long id) {
		return test1Repository.getOneTest1(id);
	}
@Override
public Iterable<Test1> listTest1(Long poOwner) {
    return test1Repository.listTest1(poOwner);
}
@Override
public Iterable<Test1> listTest1ById(Long owner) {
    return test1Repository.listTest1ById(owner);
}}