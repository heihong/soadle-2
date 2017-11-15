package fr.soat.soadle.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.soat.soadle.model.Meeting;

/**
 * @author hakim
 *
 */
public interface MeetingRepository extends JpaRepository<Meeting, String> {
	

}
