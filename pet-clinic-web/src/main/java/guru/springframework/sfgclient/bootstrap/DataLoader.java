package guru.springframework.sfgclient.bootstrap;

import guru.springframework.sfgclient.model.Owner;
import guru.springframework.sfgclient.model.Vet;
import guru.springframework.sfgclient.services.OwnerService;
import guru.springframework.sfgclient.services.VetService;
import guru.springframework.sfgclient.services.map.OwnerServiceMap;
import guru.springframework.sfgclient.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Ayse");
        owner1.setLastName("Bilir");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Veli");
        owner2.setLastName("Gider");
        ownerService.save(owner2);

        System.out.println("Loading owners ...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("John");
        vet1.setLastName("Doe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jane");
        vet2.setLastName("Smith");
        vetService.save(vet2);

        System.out.println("Loading vets ...");

    }
}
