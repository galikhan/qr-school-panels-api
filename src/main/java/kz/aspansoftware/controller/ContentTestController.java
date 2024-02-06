package kz.aspansoftware.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import kz.aspansoftware.records.Content;
import kz.aspansoftware.records.ContentTest;
import kz.aspansoftware.repository.ContentTestRepository;

import java.util.List;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/api/v1/content-test")
@CrossOrigin
public class ContentTestController {

    @Inject
    private ContentTestRepository contentTestRepository;

    @Get("/by-content/{id}")
    public List<ContentTest> findByContentId(@PathVariable Long id) {
        return contentTestRepository.findByContent(id);
    }

    @Post
    public ContentTest create(@Body ContentTest contentTest) {
        return contentTestRepository.create(contentTest);
    }

    @Put
    public ContentTest update(@Body ContentTest contentTest) {
        return contentTestRepository.update(contentTest);
    }

    @Delete("/id/{id}")
    public int remove(@PathVariable Long id) {
        return contentTestRepository.remove(id);
    }
}
