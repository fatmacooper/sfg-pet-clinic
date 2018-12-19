package guru.springframework.sfgclient.services.springdatajpa;

import guru.springframework.sfgclient.model.Owner;
import guru.springframework.sfgclient.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerSDJpaService service;

    private static final Long ID1 = 1L;
    private static final Long ID2 = 2L;
    private static final String LAST_NAME="Smith";
    private Owner owner1;
    private Set<Owner> owners;

    @BeforeEach
    void setUp() {
        owner1 = Owner.builder().id(ID1).lastName(LAST_NAME).build();
        Owner owner2 = Owner.builder().id(ID2).build();
        owners = new HashSet<>();
        owners.add(owner1);
        owners.add(owner2);
    }

    @Test
    void findByLastName() {
        when(service.findByLastName(any())).thenReturn(owner1);
        Owner smith = service.findByLastName(LAST_NAME);
        assertEquals(smith.getLastName(),LAST_NAME);
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        when(service.findAll()).thenReturn(owners);
        Set<Owner> ownerList = service.findAll();
        assertNotNull(ownerList);
        assertEquals(2,ownerList.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner1));
        Owner returnedOwner = service.findById(ID1);
        assertNotNull(returnedOwner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner returnedOwner = service.findById(ID1);
        assertNull(returnedOwner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner1);
        Owner returnedOwner = service.save(owner1);
        assertNotNull(returnedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(owner1);
        verify(ownerRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID1);
        verify(ownerRepository).deleteById(ID1);
    }
}