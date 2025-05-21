package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
// SequenceCondition의 경우 컬럼에 저장할 값은 "SEQUENCE"로 지정
@DiscriminatorValue("SEQUENCE")
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