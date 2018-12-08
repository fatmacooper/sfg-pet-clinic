package guru.springframework.sfgclient.services;

import guru.springframework.sfgclient.model.Owner;

public interface OwnerService extends CrudService<Owner,Long> {
    Owner findByLastName(String lastName);
}
