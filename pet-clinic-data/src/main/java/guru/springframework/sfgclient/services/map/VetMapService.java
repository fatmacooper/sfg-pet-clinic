package guru.springframework.sfgclient.services.map;

import guru.springframework.sfgclient.model.Specialty;
import guru.springframework.sfgclient.model.Vet;
import guru.springframework.sfgclient.services.SpecialityService;
import guru.springframework.sfgclient.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default","map"})
public class VetMapService extends AbstractMapService<Vet,Long> implements VetService {
    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        object.getSpecialties().forEach(speciality->{
            if(speciality.getId()==null){
                Specialty savedSpeciality = specialityService.save(speciality);
                speciality.setId(savedSpeciality.getId());
            }
        });
        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
