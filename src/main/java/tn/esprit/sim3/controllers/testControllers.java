package tn.esprit.sim3.controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.sim3.entites.Ticket;
import tn.esprit.sim3.entites.TrancheAge;
import tn.esprit.sim3.entites.TypeTicket;
import tn.esprit.sim3.entites.Internaute;
import tn.esprit.sim3.entites.Evenement;
import tn.esprit.sim3.services.ServicesInterfaces;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class testControllers {

	ServicesInterfaces services;

	@PostMapping("/internaute")
	public Internaute ajouterInternaute(@RequestBody Internaute internaute) {
		return services.ajouterInternaute(internaute);
	}

	@PostMapping("/evenement")
	public Evenement ajouterEvenement(@RequestBody Evenement evenement) {
		return services.ajouterEvenement(evenement);
	}

	@GetMapping("/events-par-categorie")
	public void listEventsParCategorie() {
		services.listEventsParCategorie();
	}

	@PostMapping("/{idEven}/internaute/{idInternaute}/tickets")
	public List<Ticket> ajouterTicketsEtAffecter(@RequestBody List<Ticket> tickets,
												 @PathVariable Long idEven,
												 @PathVariable Long idInternaute) {
		return services.ajouterTicketsEtAffecterAEvenementEtInternaute(tickets, idEven, idInternaute);
	}

	@GetMapping("/tickets/internaute/{id}")
	public List<Ticket> ticketsParInternaute(@PathVariable Long id) {
		return services.ticketsParInternaute(id);
	}

	@GetMapping("/count-internautes")
	public Long nbInternateParTrancheAgeEtDateEvent(@RequestParam TrancheAge trancheAge,
													@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateMin,
													@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateMax) {
		return services.nbInternateParTrancheAgeEtDateEvent(trancheAge, dateMin, dateMax);
	}

	@GetMapping("/montant")
	public Double montantRecupererParEvtEtTypeTicket(@RequestParam String nomEv,
													 @RequestParam TypeTicket typeTicket) {
		return services.montantRecupererParEvtEtTypeTicket(nomEv, typeTicket);
	}
}
