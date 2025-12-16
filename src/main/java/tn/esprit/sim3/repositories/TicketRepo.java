package tn.esprit.sim3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.sim3.entites.Ticket;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
	List<Ticket> findByInternauteIdInternaute(Long idInternaute);
	List<Ticket> findByEvenementIdEvents(Long idEvenement);
}
