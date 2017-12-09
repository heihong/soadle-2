package fr.soat.soadle.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.soat.soadle.model.Meeting;

/**
 * @author hakim
 *
 */
public interface MeetingRepository extends JpaRepository<Meeting, String> {
	

	/**
	 * @param tag
	 * @return
	 */
	@Query("select m from Meeting m "+
			"where exists(select o from Option o where m.id = o.mettnigId " +
			 " AND (:dateDebut is null OR o.date >= :dateDebut ) "+
			 " AND (:dateFin is null OR o.date <= :dateFin )"+
			 ") "  + 
			 "AND ((m.title like '%' || :tag || '%') "+
			 "     OR (m.tags is not null and m.tags like '%' || :tag || '%')) " 	  
			)	
	List<Meeting>  findByTag(@Param("tag") String tag,@Param("dateDebut") Date dateDebut,@Param("dateFin") Date dateFin);
	
			
	/**
	 * @param id
	 */
	@Modifying(clearAutomatically = true)
	@Query("update Meeting m set m.participantsCount = (select count(id) from Participant where MEETING_ID = :id ) where MEETING_ID = :id ")
	void updateParticipantsCount(@Param("id") String id);


	/**
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	@Query("select m from Meeting m "+
			"where exists(select o from Option o where m.id = o.mettnigId " +
			 " AND (:dateDebut is null OR o.date >= :dateDebut ) "+
			 " AND (:dateFin is null OR o.date <= :dateFin )"+
			")"  
			)	
	List<Meeting> findByDate(@Param("dateDebut") Date dateDebut,@Param("dateFin") Date dateFin);
	
}
