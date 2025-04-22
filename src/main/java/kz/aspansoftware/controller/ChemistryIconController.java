package kz.aspansoftware.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import kz.aspansoftware.records.ChemistryIcon;
import kz.aspansoftware.repository.ChemistryIconRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

@Controller("/api/v1/chemistry-icon")
@Secured(SecurityRule.IS_ANONYMOUS)
@CrossOrigin
@ExecuteOn(BLOCKING)
public class ChemistryIconController {

    private final ChemistryIconRepository chemistryIconRepository;
    private final Logger logger = LoggerFactory.getLogger(ChemistryIconController.class);

    public ChemistryIconController(ChemistryIconRepository chemistryIconRepository) {
        this.chemistryIconRepository = chemistryIconRepository;
    }

    @Get("/element/{element}")
    public List<ChemistryIcon> findById(@PathVariable String element) {
        logger.info("currentThread {}", Thread.currentThread().getName());
        return chemistryIconRepository.findByElement(element);
    }

}
