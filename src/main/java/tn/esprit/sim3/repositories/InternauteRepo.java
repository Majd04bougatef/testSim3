package tn.esprit.sim3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.sim3.entites.Internaute;

public interface InternauteRepo extends JpaRepository<Internaute, Long> {
}
