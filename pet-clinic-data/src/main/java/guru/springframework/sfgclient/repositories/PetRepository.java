package guru.springframework.sfgclient.repositories;

import guru.springframework.sfgclient.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet,Long> {
}
