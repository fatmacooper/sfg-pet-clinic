package guru.springframework.sfgclient.controllers;

import guru.springframework.sfgclient.model.Pet;
import guru.springframework.sfgclient.model.Visit;
import guru.springframework.sfgclient.services.PetService;
import guru.springframework.sfgclient.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class VisitController {
    private final PetService petService;
    private final VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setAllowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Map<String,Object> model){
        Pet pet = petService.findById(petId);
        model.put("pet",pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long petId, Map<String,Object> model){
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNeVisitForm(@Valid Visit visit, BindingResult result){
        if(result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }else{
            Visit savedVisit = visitService.save(visit);
            return "redirect:/owners/" + savedVisit.getPet().getOwner().getId();
        }
    }
}