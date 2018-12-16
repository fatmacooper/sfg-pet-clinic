package guru.springframework.sfgclient.repositories;

import guru.springframework.sfgclient.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty,Long> {
}
