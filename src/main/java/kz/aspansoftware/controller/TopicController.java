package kz.aspansoftware.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;
import kz.aspansoftware.records.Topic;
import kz.aspansoftware.repository.TopicRepository;

import java.util.List;

@Controller("/api/v1/topic")
@CrossOrigin
public class TopicController {

    @Inject
    private TopicRepository topicRepository;

    @Post
    public Topic create(@Body Topic topic) {
        return topicRepository.create(topic);
    }

    @Put
    public Topic update(@Body Topic topic) {
        return topicRepository.update(topic);
    }

    @Get("{id}")
    public Topic findById(@PathVariable Long id) {
        return topicRepository.findById(id);
    }

    @Get("/view/all")
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Get("/parent/{id}")
    public List<Topic> findByParent(Long id) {
        return topicRepository.findByParent(id);
    }

    @Delete("/{id}")
    public int remove(Long id) {
        return topicRepository.remove(id);
    }
}
