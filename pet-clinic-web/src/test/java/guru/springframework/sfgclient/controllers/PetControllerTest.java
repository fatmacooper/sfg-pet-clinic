package guru.springframework.sfgclient.controllers;

import guru.springframework.sfgclient.model.Owner;
import guru.springframework.sfgclient.model.Pet;
import guru.springframework.sfgclient.model.PetType;
import guru.springframework.sfgclient.services.OwnerService;
import guru.springframework.sfgclient.services.PetService;
import guru.springframework.sfgclient.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;
    Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1l).build();

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1l).name("Dog").build());
        petTypes.add(PetType.builder().id(2l).name("Cat").build());

        mockMvc = MockMvcBuilders.
                standaloneSetup(petController).
                build();
    }
    @Test
    void initCreationForm() throws Exception{
        //given
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        //when
        mockMvc.perform(get("/owners/1/pets/new")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("owner")).
                andExpect(model().attributeExists("pet")).
                andExpect(view().name("pets/createOrUpdatePetForm"));
    }
    @Test
    void processCreationForm() throws Exception{
        //given
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        //when
        mockMvc.perform(post("/owners/1/pets/new")).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateForm() throws Exception{
        //given
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        //when
        mockMvc.perform(get("/owners/1/pets/1/edit")).
                andExpect(status().isOk()).
                andExpect(model().attributeExists("owner")).
                andExpect(model().attributeExists("pet")).
                andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processUpdateForm() throws Exception{
        //given
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        //when
        mockMvc.perform(post("/owners/1/pets/1/edit")).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/owners/1"));
        verify(petService).save(ArgumentMatchers.any());
    }
}