package guru.springframework.sfgclient.services.map;

import guru.springframework.sfgclient.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    private PetMapService petMapService;
    private final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> pets =  petMapService.findAll();
        assertEquals(pets.size(),1);
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);
        assertEquals(pet.getId(),petId);
    }

    @Test
    void findByIdNotExisting() {
        Pet pet = petMapService.findById(2L);
        assertNull(pet);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Pet pet = Pet.builder().id(id).build();
        Pet savedPet = petMapService.save(pet);
        assertNotNull(savedPet);
        assertEquals(savedPet.getId(),id);
    }

    @Test
    void saveDuplicateId() {
        Long id = 1L;
        Pet pet = Pet.builder().id(id).build();
        Pet savedPet = petMapService.save(pet);
        assertNotNull(savedPet);
        assertEquals(savedPet.getId(),id);
        assertEquals(1,petMapService.findAll().size());
    }

    @Test
    void saveNoId(){
        Pet savedPet = petMapService.save(Pet.builder().build());
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2,petMapService.findAll().size());
    }

    @Test
    void deletePet() {
        petMapService.delete(petMapService.findById(petId));
        assertEquals(0,petMapService.findAll().size());
    }

    @Test
    void deleteWithWrongId() {
        Pet pet = Pet.builder().id(5L).build();
        petMapService.delete(pet);
        assertEquals(1,petMapService.findAll().size());
    }

    @Test
    void deleteWithNullId(){
        Pet pet = Pet.builder().build();
        petMapService.delete(pet);
        assertEquals(1,petMapService.findAll().size());
    }

    @Test
    void deleteWithCorrectId() {
        petMapService.deleteById(petId);
        assertEquals(0,petMapService.findAll().size());
    }

    @Test
    void deleteByWrongId() {
        petMapService.deleteById(5L);
        assertEquals(1,petMapService.findAll().size());
    }

    @Test
    void deleteByNullId() {
        petMapService.deleteById(null);
        assertEquals(1,petMapService.findAll().size());
    }
}