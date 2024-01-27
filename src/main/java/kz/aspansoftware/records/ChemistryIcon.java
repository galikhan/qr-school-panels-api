package kz.aspansoftware.records;

import io.micronaut.serde.annotation.Serdeable;
import kz.jooq.model.tables.records.ChemistryIconRecord;

@Serdeable
public record ChemistryIcon(Long id, String element, String name, String path) {
    public static ChemistryIcon to(ChemistryIconRecord record) {
        return new ChemistryIcon(record.getId_(), record.getElement_(), record.getName_(), record.getPath_());
    }
}
