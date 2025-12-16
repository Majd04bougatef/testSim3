package tn.esprit.sim3.entites;




import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEvents;

    String nom;
    Long nbPlacesRestants;
    LocalDate dateEvents;

    @OneToMany(mappedBy = "evenement")
    List<Ticket> listt;

    @ManyToMany
    List<Categorie> listc;
}
