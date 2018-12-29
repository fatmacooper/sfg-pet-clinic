package guru.springframework.sfgclient.controllers;

import guru.springframework.sfgclient.model.Owner;
import guru.springframework.sfgclient.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@RequestMapping({"/owners"})
@Controller
public class OwnerController {
    private final OwnerService ownerService;
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateForm";
    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /*@RequestMapping({"","/index","/index.html"})
    public String listOwners(Model model){
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }*/

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result,Model model){
        if(owner.getLastName() == null){
            owner.setLastName("");
        }
        Set<Owner> owners = ownerService.findAllByLastNameLike('%' + owner.getLastName() + '%');
        if(owners.isEmpty()){
            result.rejectValue("lastName","notFound","not found");
            return "owners/findOwners";
        }else if(owners.size() == 1){
            owner = owners.iterator().next();
            model.addAttribute("owner",owner);
            return "redirect:/owners/" + owner.getId();
        }else{
            model.addAttribute("selections",owners);
            return "owners/ownersList";
        }
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else{
           Owner savedOwner = ownerService.save(owner);
           return "redirect:/owners/"+ savedOwner.getId();

        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateForm(@PathVariable Long ownerId,Model model){
        model.addAttribute("owner",ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(@Valid Owner owner, BindingResult result,@PathVariable Long ownerId){
        if(result.hasErrors()){
            return  VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else{
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

   /* @RequestMapping({"/find"})
    public String notImplemented(){
       return "notimplemented";
    }*/
}