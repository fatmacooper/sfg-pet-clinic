package guru.springframework.sfgclient.repositories;

import guru.springframework.sfgclient.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
}
