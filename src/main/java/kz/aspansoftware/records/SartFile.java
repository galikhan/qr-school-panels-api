package kz.aspansoftware.records;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.FileRecord;

import java.time.LocalDateTime;

@Serdeable
public record SartFile(Long id, Long container, String containerClass, String uuid, String filename, String filepath, LocalDateTime created, LocalDateTime modified, Boolean isRemoved) {
    public static SartFile toFile(FileRecord r) {
        return new SartFile(r.getId_(),
                r.getContainer_(),
                r.getContainerClass_(),
                r.getUuid_(),
                r.getFilename_(),
                r.getFilepath_(),
                r.getCreated_(),
                r.getUpdated_(),
                r.getIsRemoved_()
        );
    }
}
