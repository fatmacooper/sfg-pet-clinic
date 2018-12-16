package guru.springframework.sfgclient.repositories;

import guru.springframework.sfgclient.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
