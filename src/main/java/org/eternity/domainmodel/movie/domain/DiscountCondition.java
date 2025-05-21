package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@SequenceGenerator(
        name = "condition_sequence",
        sequenceName = "condition_sequence",
        initialValue = 1, allocationSize = 50
)
public abstract class DiscountCondition {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_sequence")
    private Long id;

    private DiscountPolicy policy;

    abstract public void load();
}
