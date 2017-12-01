package fr.soat.soadle.repositories;


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
	@Query("select m from Meeting m where (m.title like '%' || :tag || '%') OR (m.tags is not null and m.tags like '%' || :tag || '%')")	
	List<Meeting>  findByTag(@Param("tag") String tag);
	
	
	/**
	 * @param id
	 * @param tags
	 */
	@Modifying(clearAutomatically = true)
	@Query("update Meeting m set m.tags = :tags where m.id = :id")
	void saveTags(@Param("id") String id, @Param("tags") String tags);
	
}
