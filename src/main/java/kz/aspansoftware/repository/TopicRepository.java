package kz.aspansoftware.repository;

import jakarta.inject.Singleton;
import kz.aspansoftware.records.Topic;
import kz.jooq.model.tables.records.TopicRecord;
import org.jooq.DSLContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Topic.TOPIC;
import static org.jooq.Records.mapping;

@Singleton
public class TopicRepository {

    private final DSLContext dsl;

    public TopicRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Topic create(Topic topic) {
        return this.dsl
                .insertInto(TOPIC)
                .set(TOPIC.NAME_, topic.name())
                .set(TOPIC.PARENT_, topic.parent())
                .set(TOPIC.CREATED_, LocalDateTime.now())
                .set(TOPIC.TYPE_, topic.type())
                .set(TOPIC.TOPIC.IS_REMOVED_, topic.isRemoved())
                .set(TOPIC.ICON_TYPE_, topic.iconType())
                .returningResult(TOPIC.ID_, TOPIC.NAME_, TOPIC.PARENT_, TOPIC.TYPE_, TOPIC.IS_REMOVED_, TOPIC.ICON_TYPE_)
                .fetchOne(mapping(Topic::new));
    }

    public Topic update(Topic topic) {
        return this.dsl
                .update(TOPIC)
                .set(TOPIC.ID_, topic.id())
                .set(TOPIC.NAME_, topic.name())
                .set(TOPIC.PARENT_, topic.parent())
                .set(TOPIC.CREATED_, LocalDateTime.now())
                .set(TOPIC.TYPE_, topic.type())
                .set(TOPIC.TOPIC.IS_REMOVED_, topic.isRemoved())
                .set(TOPIC.ICON_TYPE_, topic.iconType())
                .where(TOPIC.ID_.eq(topic.id()))
                .returningResult(TOPIC.ID_, TOPIC.NAME_, TOPIC.PARENT_, TOPIC.TYPE_, TOPIC.IS_REMOVED_, TOPIC.ICON_TYPE_)
                .fetchOne(mapping(Topic::new));
    }

    public int remove(Long id) {
        return this.dsl.update(TOPIC).set(TOPIC.IS_REMOVED_, true).where(TOPIC.ID_.eq(id)).execute();
    }

    public List<Topic> findByParent(Long id) {
        return this.dsl.selectFrom(TOPIC)
                .where(TOPIC.IS_REMOVED_.eq(false).and(TOPIC.PARENT_.eq(id)))
                .orderBy(TOPIC.ID_)
                .stream().map(Topic::toTopic).collect(Collectors.toList());
    }

    public Optional<TopicRecord> findRecordById(Long id) {
        return this.dsl.selectFrom(TOPIC)
                .where(TOPIC.ID_.eq(id))
                .fetchOptional();
    }

    public Topic findById(Long id) {
        var record = findRecordById(id);
        if(record.isPresent()) {
            return Topic.toTopic(record.get());
        }
        return null;
    }

    public List<Topic> findAll() {
        return this.dsl.selectFrom(TOPIC)
                .fetch().stream().map(Topic::toTopic).collect(Collectors.toList());
    }
}
