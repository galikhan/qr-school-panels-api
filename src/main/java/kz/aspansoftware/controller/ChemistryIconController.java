package kz.aspansoftware.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import kz.aspansoftware.records.ChemistryIcon;
import kz.aspansoftware.repository.ChemistryIconRepository;

import java.util.List;

@Controller("/api/v1/chemistry-icon")
@Secured(SecurityRule.IS_ANONYMOUS)
@CrossOrigin
public class ChemistryIconController {

    @Inject
    private ChemistryIconRepository chemistryIconRepository;

    @Get("/element/{element}")
    public List<ChemistryIcon> findById(@PathVariable String element) {
        return chemistryIconRepository.findByElement(element);
    }

}
