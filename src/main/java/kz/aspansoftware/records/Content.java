package kz.aspansoftware.records;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.ContentRecord;

@Serdeable
public record Content(Long id, Long topic, String type, String body, String input, Boolean isRemoved, int editorLen, Integer taskCounter) {
    public static Content toContent(ContentRecord record) {
        return new Content(record.getId_(),
                record.getTopic_(),
                record.getType_(),
                record.getBody_(),
                record.getInput_(),
                record.getIsRemoved_(),
                record.getEditorLen_(),
                record.getTaskCounter_());
    }
}
