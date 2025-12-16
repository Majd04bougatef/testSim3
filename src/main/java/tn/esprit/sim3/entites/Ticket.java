package tn.esprit.sim3.entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Type;
import java.util.List;



@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTicket;

    @Enumerated(EnumType.STRING)
    TypeTicket typeTicket;
    String codeTicket;
    Double prixTicket;

    @ManyToOne
    Evenement evenement;

    @ManyToOne
    Internaute internaute;
}
