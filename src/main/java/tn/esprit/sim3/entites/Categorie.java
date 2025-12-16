package tn.esprit.sim3.entites;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "categorie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCategorie;

    String codeCategorie;
    String nomCategorie;

    @ManyToMany(mappedBy = "listc")
    List<Evenement> liste;
}
