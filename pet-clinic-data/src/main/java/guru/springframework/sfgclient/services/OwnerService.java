package guru.springframework.sfgclient.services;

import guru.springframework.sfgclient.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner,Long> {
    Owner findByLastName(String lastName);
    Set<Owner> findAllByLastNameLike(String lastName);
}
