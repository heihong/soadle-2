package fr.soat.soadle.repositories;

import fr.soat.soadle.doodle.Doodle;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DoodleQueryRepository {

    Collection<Doodle> getAll();
}
