package fr.soat.soadle.services;

import fr.soat.soadle.doodle.Doodle;
import fr.soat.soadle.repositories.DoodleQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DoodleQueryService {

    @Autowired
    private DoodleQueryRepository doodleQueryRepository;

    public Collection<Doodle> getAllDoodles() {
        return doodleQueryRepository.getAll();
    }

}
