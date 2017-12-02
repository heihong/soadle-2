package fr.soat.soadle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.soat.soadle.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	
	
	@Query("select p from Participant p where MEETING_ID = :idMeeting and p.email = :email ")	
	List<Participant>  findByEmail(@Param("idMeeting") String idMeeting, @Param("email") String email);

	@Query("select p from Participant p where MEETING_ID = :idMeeting and p.doodleId = :doodleId ")	
	List<Participant>  findByDoodleId(@Param("idMeeting") String idMeeting, @Param("doodleId") String doodleId);
}
