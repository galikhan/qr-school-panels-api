package kz.aspansoftware.repository;

import jakarta.inject.Singleton;
import kz.aspansoftware.records.Content;
import kz.aspansoftware.records.ContentTest;
import org.jooq.DSLContext;

import java.util.List;
import java.util.stream.Collectors;

import static kz.jooq.model.tables.ContentTest.CONTENT_TEST;
import static org.jooq.Records.mapping;

@Singleton
public class ContentTestRepository {

    private final DSLContext dsl;

    public ContentTestRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public ContentTest create(ContentTest content) {
        return this.dsl
                .insertInto(CONTENT_TEST)
                .set(CONTENT_TEST.CONTENT_, content.content())
                .set(CONTENT_TEST.INPUT_, content.input())
                .set(CONTENT_TEST.OUTPUT_, content.output())
                .returningResult(CONTENT_TEST.ID_,
                        CONTENT_TEST.CONTENT_,
                        CONTENT_TEST.INPUT_,
                        CONTENT_TEST.OUTPUT_,
                        CONTENT_TEST.IS_REMOVED_
                )
                .fetchOne(mapping(ContentTest::new));
    }

    public ContentTest update(ContentTest content) {
        return this.dsl
                .update(CONTENT_TEST)
                .set(CONTENT_TEST.CONTENT_, content.content())
                .set(CONTENT_TEST.INPUT_, content.input())
                .set(CONTENT_TEST.OUTPUT_, content.output())
                .where(CONTENT_TEST.ID_.eq(content.id()))
                .returningResult(CONTENT_TEST.ID_,
                        CONTENT_TEST.CONTENT_,
                        CONTENT_TEST.INPUT_,
                        CONTENT_TEST.OUTPUT_,
                        CONTENT_TEST.IS_REMOVED_
                )
                .fetchOne(mapping(ContentTest::new));
    }

    public int remove(Long id) {
        return this.dsl.update(CONTENT_TEST).set(CONTENT_TEST.IS_REMOVED_, true).where(CONTENT_TEST.ID_.eq(id)).execute();
    }

    public List<ContentTest> findByContent(Long id) {
        return this.dsl.selectFrom(CONTENT_TEST)
                .where(CONTENT_TEST.IS_REMOVED_.eq(false)
                        .and(CONTENT_TEST.CONTENT_.eq(id)))
                .orderBy(CONTENT_TEST.ID_)
                .stream().map(ContentTest::to).collect(Collectors.toList());
    }

//    public Optional<ContentRecord> findRecordById(Long id) {
//        return this.dsl.selectFrom(CONTENT)
//                .where(CONTENT_TEST.ID_.eq(id))
//                .fetchOptional();
//    }
//
//    public Content findById(Long id) {
//        var record = findRecordById(id);
//        if (record.isPresent()) {
//            return Content.toContent(record.get());
//        }
//        return null;
//    }
//
//    public List<Content> findAll() {
//        return this.dsl.selectFrom(CONTENT)
//                .fetch().stream().map(Content::toContent).collect(Collectors.toList());
//    }
}
