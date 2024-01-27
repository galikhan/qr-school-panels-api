package kz.aspansoftware.repository;

import jakarta.inject.Singleton;
import kz.aspansoftware.records.Content;
import kz.jooq.model.tables.records.ContentRecord;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.Content.CONTENT;
import static org.jooq.Records.mapping;

@Singleton
public class ContentRepository {

    private final DSLContext dsl;

    public ContentRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Content create(Content content) {
        return this.dsl
                .insertInto(CONTENT)
                .set(CONTENT.TOPIC_, content.topic())
                .set(CONTENT.TYPE_, content.type())
                .set(CONTENT.BODY_, content.body())
                .set(CONTENT.INPUT_, content.input())
                .returningResult(CONTENT.ID_, CONTENT.TOPIC_, CONTENT.TYPE_, CONTENT.BODY_, CONTENT.INPUT_, CONTENT.IS_REMOVED_)
                .fetchOne(mapping(Content::new));
    }

    public Content update(Content content) {
        return this.dsl
                .update(CONTENT)
                .set(CONTENT.TOPIC_, content.topic())
                .set(CONTENT.TYPE_, content.type())
                .set(CONTENT.BODY_, content.body())
                .set(CONTENT.INPUT_, content.input())
                .set(CONTENT.IS_REMOVED_, content.isRemoved())
                .where(CONTENT.ID_.eq(content.id()))
                .returningResult(CONTENT.ID_, CONTENT.TOPIC_, CONTENT.TYPE_, CONTENT.BODY_, CONTENT.INPUT_, CONTENT.IS_REMOVED_)
                .fetchOne(mapping(Content::new));
    }

    public int remove(Long id) {
        return this.dsl.update(CONTENT).set(CONTENT.IS_REMOVED_, true).where(CONTENT.ID_.eq(id)).execute();
    }

    public List<Content> findByTopic(Long id) {
        return this.dsl.selectFrom(CONTENT)
                .where(CONTENT.IS_REMOVED_.eq(false).and(CONTENT.TOPIC_.eq(id)))
                .orderBy(CONTENT.ID_)
                .stream().map(Content::toContent).collect(Collectors.toList());
    }

    public Optional<ContentRecord> findRecordById(Long id) {
        return this.dsl.selectFrom(CONTENT)
                .where(CONTENT.ID_.eq(id))
                .fetchOptional();
    }

    public Content findById(Long id) {
        var record = findRecordById(id);
        if (record.isPresent()) {
            return Content.toContent(record.get());
        }
        return null;
    }

    public List<Content> findAll() {
        return this.dsl.selectFrom(CONTENT)
                .fetch().stream().map(Content::toContent).collect(Collectors.toList());
    }
}
