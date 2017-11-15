package fr.soat.soadle.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.soat.soadle.model.Soadle;

/**
 * @author hakim
 *
 */
public interface SoadleRepository extends JpaRepository<Soadle, String> {
	

}
