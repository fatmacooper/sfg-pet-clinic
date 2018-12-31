package guru.springframework.sfgclient.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pets")
public class Pet extends BaseEntity{
    @Builder
    public Pet(Long id, String name, LocalDate birthDate, PetType petType, Owner owner, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
        if(visits == null || visits.size()>0){
            this.visits = visits;
        }
    }

    @Column(name="name")
    private String name;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @ManyToOne
    @JoinColumn(name="type_id")
    private PetType petType;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();
}