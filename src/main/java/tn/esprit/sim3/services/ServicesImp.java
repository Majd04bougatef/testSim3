package tn.esprit.sim3.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.sim3.entites.*;
import tn.esprit.sim3.repositories.CategorieRepo;
import tn.esprit.sim3.repositories.EventementRepo;
import tn.esprit.sim3.repositories.InternauteRepo;
import tn.esprit.sim3.repositories.TicketRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ServicesImp implements ServicesInterfaces {

    InternauteRepo internauteRepo;
    EventementRepo eventementRepo;
    TicketRepo ticketRepo;
    CategorieRepo categorieRepo;

    // scheduled task: run every day at midnight
    @org.springframework.scheduling.annotation.Scheduled(cron = "0 0 0 * * ?")
    public void scheduledListEventsParCategorie() {
        System.out.println("[Scheduled] Running listEventsParCategorie at " + java.time.LocalDateTime.now());
        listEventsParCategorie();
    }

    @Override
    public Internaute ajouterInternaute(Internaute internaute) {
        return internauteRepo.save(internaute);
    }

    @Override
    public Evenement ajouterEvenement(Evenement evenement) {
        return eventementRepo.save(evenement);
    }

    @Override
    public void listEventsParCategorie() {
        List<Categorie> categories = categorieRepo.findAll();
        for (Categorie c : categories) {
            System.out.println("Categorie: " + c.getNomCategorie());
            List<Evenement> events = c.getListe();
            if (events != null) {
                for (Evenement e : events) {
                    System.out.println("  - " + e.getNom());
                }
            }
        }
    }

    @Override
    public List<Ticket> ajouterTicketsEtAffecterAEvenementEtInternaute(List<Ticket> tickets, Long idEven, Long idInternaute) {
        Evenement ev = eventementRepo.findById(idEven).orElseThrow(() -> new RuntimeException("Evenement not found"));
        Internaute in = internauteRepo.findById(idInternaute).orElseThrow(() -> new RuntimeException("Internaute not found"));

        // associate and save
        for (Ticket t : tickets) {
            t.setEvenement(ev);
            t.setInternaute(in);
        }

        // reduce available places
        long toBook = tickets.size();
        if (ev.getNbPlacesRestants() == null) ev.setNbPlacesRestants(0L);
        ev.setNbPlacesRestants(Math.max(0L, ev.getNbPlacesRestants() - toBook));
        eventementRepo.save(ev);

        List<Ticket> saved = ticketRepo.saveAll(tickets);
        return saved;
    }

    @Override
    public List<Ticket> ticketsParInternaute(Long idInternaute) {
        return ticketRepo.findByInternauteIdInternaute(idInternaute);
    }

    @Override
    public Long nbInternateParTrancheAgeEtDateEvent(TrancheAge trancheAge, LocalDate dateMin, LocalDate dateMax) {
        List<Ticket> all = ticketRepo.findAll();
        return all.stream()
                .filter(Objects::nonNull)
                .filter(t -> t.getInternaute() != null && t.getInternaute().getTrancheAge() == trancheAge)
                .filter(t -> t.getEvenement() != null && t.getEvenement().getDateEvents() != null)
                .filter(t -> !t.getEvenement().getDateEvents().isBefore(dateMin) && !t.getEvenement().getDateEvents().isAfter(dateMax))
                .map(t -> t.getInternaute().getIdInternaute())
                .distinct()
                .count();
    }

    @Override
    public Double montantRecupererParEvtEtTypeTicket(String nomEv, TypeTicket typeTicket) {
        List<Ticket> all = ticketRepo.findAll();
        return all.stream()
                .filter(t -> t.getEvenement() != null && nomEv.equals(t.getEvenement().getNom()))
                .filter(t -> t.getTypeTicket() == typeTicket)
                .map(Ticket::getPrixTicket)
                .filter(Objects::nonNull)
                .reduce(0.0, Double::sum);
    }
}
