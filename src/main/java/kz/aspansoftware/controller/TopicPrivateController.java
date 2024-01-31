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
import kz.aspansoftware.records.Topic;
import kz.aspansoftware.repository.TopicRepository;

import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/v1/private/topic")
@CrossOrigin
public class TopicPrivateController {

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

    @Delete("/{id}")
    public int remove(Long id) {
        return topicRepository.remove(id);
    }
}
