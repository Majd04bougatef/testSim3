package tn.esprit.sim3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.sim3.entites.Categorie;

public interface CategorieRepo extends JpaRepository<Categorie, Long> {
}
