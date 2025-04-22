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

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/v1/private/content")
@CrossOrigin
@ExecuteOn(BLOCKING)
public class ContentPrivateController {

    @Inject
    private ContentRepository contentRepository;

    @Post
    public Content create(@Body Content content) {
        return contentRepository.create(content);
    }

    @Put
    public Content update(@Body Content content) {
        return contentRepository.update(content);
    }

    @Delete("/{id}")
    public int remove(Long id) {
        return contentRepository.remove(id);
    }
}
