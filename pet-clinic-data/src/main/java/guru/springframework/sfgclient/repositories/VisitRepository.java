package guru.springframework.sfgclient.repositories;

import guru.springframework.sfgclient.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
