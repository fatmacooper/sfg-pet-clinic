package guru.springframework.sfgclient.services;

import guru.springframework.sfgclient.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
