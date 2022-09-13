
package sn.gainde2000.pi.core.service;
import sn.gainde2000.pi.core.entity.Test1;
import java.util.List;
import java.util.Optional;

public interface ITest1Service {

Optional<Test1> findById(Long id);
List<Test1> findAll();
Test1 save(Test1 test1);
void delete(Test1 test1);
Iterable<Test1> listTest1(Long poOwner);
Iterable<Test1> listTest1ById(Long owner);
Test1 getOneTest1(Long id);}