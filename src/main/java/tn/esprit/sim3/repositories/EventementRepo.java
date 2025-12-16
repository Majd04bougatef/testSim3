package tn.esprit.sim3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.sim3.entites.Evenement;

public interface EventementRepo extends JpaRepository<Evenement, Long> {
}
