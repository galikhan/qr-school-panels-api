package kz.aspansoftware.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import kz.aspansoftware.records.Content;
import kz.aspansoftware.repository.ContentRepository;

import java.util.List;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/api/v1/content")
@CrossOrigin
@ExecuteOn(BLOCKING)
public class ContentController {

    @Inject
    private ContentRepository contentRepository;

    @Get("{id}")
    public Content findById(@PathVariable Long id) {
        return contentRepository.findById(id);
    }

    @Get("/view/all")
    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    @Get("/topic/{id}")
    public List<Content> findByParent(Long id) {
        return contentRepository.findByTopic(id);
    }

}
