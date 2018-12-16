package guru.springframework.sfgclient.repositories;

import guru.springframework.sfgclient.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet,Long> {
}
