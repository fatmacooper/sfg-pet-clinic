package guru.springframework.sfgclient.repositories;

import guru.springframework.sfgclient.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
    Owner findByLastName(String lastName);
    Set<Owner> findByLastNameLike(String lastName);
}
