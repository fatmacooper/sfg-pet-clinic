package guru.springframework.sfgclient.bootstrap;

import guru.springframework.sfgclient.model.Owner;
import guru.springframework.sfgclient.model.Pet;
import guru.springframework.sfgclient.model.PetType;
import guru.springframework.sfgclient.model.Vet;
import guru.springframework.sfgclient.services.OwnerService;
import guru.springframework.sfgclient.services.PetTypeService;
import guru.springframework.sfgclient.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);


        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Ayse");
        owner1.setLastName("Bilir");
        owner1.setAddress("790 Eves Dr Unit 5");
        owner1.setCity("Hillsborough");
        owner1.setTelephone("1231231234");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Veli");
        owner2.setLastName("Gider");
        owner2.setAddress("11 Tiffany Ct");
        owner2.setCity("Cedar Grove");
        owner2.setTelephone("1231231235");

        Pet veliscat = new Pet();
        veliscat.setName("kimyon");
        veliscat.setOwner(owner2);
        veliscat.setPetType(savedCatPetType);
        veliscat.setBirthDate(LocalDate.now());
        owner2.getPets().add(veliscat);
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
