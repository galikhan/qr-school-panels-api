package kz.aspansoftware.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;
import kz.aspansoftware.records.Content;
import kz.aspansoftware.repository.ContentRepository;

import java.util.List;

@Controller("/api/v1/content")
public class ContentController {

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

    @Delete("/{id}")
    public int remove(Long id) {
        return contentRepository.remove(id);
    }
}
