package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@SequenceGenerator(
        name = "condition_sequence",
        sequenceName = "condition_sequence",
        initialValue = 1, allocationSize = 50
)
@Getter
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(generator = "condition_sequence")
    private Long id;

    private DiscountPolicy policy;

    abstract public void load();
}
