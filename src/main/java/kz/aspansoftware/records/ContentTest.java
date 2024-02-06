package kz.aspansoftware.records;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.ContentTestRecord;

@Serdeable
public record ContentTest(Long id, Long content, String input, String  output, Boolean isRemoved) {
    public static ContentTest to(ContentTestRecord record) {
        return new ContentTest(record.getId_(), record.getContent_(), record.getInput_(), record.getOutput_(), record.getIsRemoved_());
    }
}
