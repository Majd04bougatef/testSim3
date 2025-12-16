package tn.esprit.sim3.entites;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Entity
@Table(name = "intenauet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Internaute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idInternaute;

    @Enumerated(EnumType.STRING)
    TrancheAge trancheAge;
    String identifiant;

    @OneToMany(mappedBy = "internaute")
    List<Ticket> listt;
}
