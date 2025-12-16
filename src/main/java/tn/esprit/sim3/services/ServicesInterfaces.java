package tn.esprit.sim3.services;

import tn.esprit.sim3.entites.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServicesInterfaces {
    Internaute ajouterInternaute (Internaute internaute);
    Evenement  ajouterEvenement(Evenement evenement);
    void listEventsParCategorie ();
    List<Ticket> ajouterTicketsEtAffecterAEvenementEtInternaute (List<Ticket> tickets, Long idEven, Long idInternaute);
    List<Ticket> ticketsParInternaute (Long idInternaute);
    Long nbInternateParTrancheAgeEtDateEvent (TrancheAge trancheAge, LocalDate dateMin, LocalDate dateMax);
    Double montantRecupererParEvtEtTypeTicket(String nomEv, TypeTicket typeTicket);
}
