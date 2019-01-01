package guru.springframework.sfgclient.controllers;

import guru.springframework.sfgclient.model.Owner;
import guru.springframework.sfgclient.model.Pet;
import guru.springframework.sfgclient.model.Visit;
import guru.springframework.sfgclient.services.PetService;
import guru.springframework.sfgclient.services.VisitService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    @Mock
    PetService petService;
    @Mock
    VisitService visitService;
    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).
                visits(new HashSet<>()).owner(Owner.builder().id(1L).build()).build());
        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void initNewVisitForm() throws Exception{
        mockMvc.perform(get("/owners/1/pets/1/visits/new")).
                andExpect(status().isOk()).
                andExpect(view().name("pets/createOrUpdateVisitForm"));
        verifyZeroInteractions(visitService);
    }

    @Test
    void processNewVisitForm() throws Exception{
        when(visitService.save(ArgumentMatchers.any())).
                thenReturn(Visit.builder().pet(Pet.builder().id(1L).owner(Owner.builder().id(1L).build()).build()).build());
        mockMvc.perform(post("/owners/1/pets/1/visits/new")).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("visit"));
    }
}