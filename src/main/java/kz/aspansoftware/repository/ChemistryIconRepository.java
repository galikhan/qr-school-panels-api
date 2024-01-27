package kz.aspansoftware.repository;

import jakarta.inject.Singleton;
import kz.aspansoftware.records.ChemistryIcon;
import org.jooq.DSLContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static kz.jooq.model.tables.ChemistryIcon.CHEMISTRY_ICON;

@Singleton
public class ChemistryIconRepository {

    private final DSLContext dsl;

    public ChemistryIconRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

//    public ChemistryIcon create(ChemistryIcon chemistryIcon) {
//        return this.dsl
//                .insertInto(CHEMISTRY_ICON)
//                .set(CHEMISTRY_ICON.NAME_, chemistryIcon.name())
//                .set(CHEMISTRY_ICON.ELEMENT_, chemistryIcon.element())
//                .set(CHEMISTRY_ICON.PATH_, chemistryIcon.path())
//                .set(CHEMISTRY_ICON.CREATED_, LocalDateTime.now())
//                .returningResult(CHEMISTRY_ICON.ID_, CHEMISTRY_ICON.NAME_, CHEMISTRY_ICON.ELEMENT_, CHEMISTRY_ICON.PATH_)
//                .fetchOne(mapping(ChemistryIcon::new));
//    }

    public List<ChemistryIcon> findByElement(String element) {
        return this.dsl.selectFrom(CHEMISTRY_ICON)
                .where(CHEMISTRY_ICON.ELEMENT_.eq(element))
                .stream().map(ChemistryIcon::to).collect(Collectors.toList());
    }
//
//    public Optional<TopicRecord> findRecordById(Long id) {
//        return this.dsl.selectFrom(TOPIC)
//                .where(TOPIC.ID_.eq(id))
//                .fetchOptional();
//    }
//
//    public Topic findById(Long id) {
//        var record = findRecordById(id);
//        if(record.isPresent()) {
//            return Topic.toTopic(record.get());
//        }
//        return null;
//    }
//
//    public List<Topic> findAll() {
//        return this.dsl.selectFrom(TOPIC)
//                .fetch().stream().map(Topic::toTopic).collect(Collectors.toList());
//    }
}
