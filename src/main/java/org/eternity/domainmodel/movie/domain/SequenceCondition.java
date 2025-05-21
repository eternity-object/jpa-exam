package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
@DiscriminatorValue("SEQUENCE")
@SecondaryTable(name = "SEQUENCE_TABLE", pkJoinColumns = @PrimaryKeyJoinColumn(name = "SEQUENCE_ID"))
@Entity
@NoArgsConstructor @Getter
public class SequenceCondition extends DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public void load() {
        this.sequence = 0;
    }
}
