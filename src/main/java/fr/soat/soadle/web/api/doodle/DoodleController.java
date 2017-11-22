package fr.soat.soadle.web.api.doodle;

import fr.soat.soadle.services.DoodleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import static fr.soat.soadle.web.api.doodle.DoodleWebRepresentation.mapper;

/**
 *
 */
@RestController
@RequestMapping("/doodles/{version:v1}")
public class DoodleController {

    @Autowired
    private DoodleQueryService doodleQueryService;

    @RequestMapping(value = "/doodles", method = RequestMethod.GET)
    public @ResponseBody Collection<DoodleWebRepresentation> list() {
        return doodleQueryService.getAllDoodles()
                .stream()
                .map(mapper())
                .collect(Collectors.toSet());
    }
}
