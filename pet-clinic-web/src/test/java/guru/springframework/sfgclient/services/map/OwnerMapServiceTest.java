package guru.springframework.sfgclient.services.map;

import guru.springframework.sfgclient.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {
    private OwnerMapService ownerMapService;
    private Long ownerId = 1L;
    private String lastName = "smith";
    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(),new PetMapService());
        ownerMapService.save(new Owner().builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(owners.size(),1);
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertNotNull(owner);
        assertEquals(owner.getId(),ownerId);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(savedOwner.getId(),id);
    }

    @Test
    void saveNoId() {
        Owner owner2 = ownerMapService.save(Owner.builder().build());
        assertNotNull(owner2);
        assertNotNull(owner2.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(owner.getLastName(),lastName);
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("foo");
        assertNull(owner);
    }
}