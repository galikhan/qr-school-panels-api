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
import kz.aspansoftware.records.Topic;
import kz.aspansoftware.repository.TopicRepository;

import java.util.List;

import static io.micronaut.scheduling.TaskExecutors.BLOCKING;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/api/v1/topic")
@CrossOrigin
@ExecuteOn(BLOCKING)
public class TopicController {

    @Inject
    private TopicRepository topicRepository;

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

}
