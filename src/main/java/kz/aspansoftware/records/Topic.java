package kz.aspansoftware.records;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.TopicRecord;

import java.time.LocalDateTime;

@Serdeable
public record Topic(Long id, String name, Long parent, String type, Boolean isRemoved/*LocalDateTime created, LocalDateTime modified*/) {

    public static Topic toTopic(TopicRecord record) {
        return new Topic(record.getId_(), record.getName_(), record.getParent_(), record.getType_(), record.getIsRemoved_());
    }
}
