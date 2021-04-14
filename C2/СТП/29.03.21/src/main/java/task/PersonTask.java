package task;

import entity.Person;
import java.util.List;

public interface PersonTask {

    Person create(Person person);

    List<Person> readAll();

    Person read(int id);

    boolean update(Person person, int id);

    boolean delete(int id);
}
