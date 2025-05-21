package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@SequenceGenerator(
        name = "discount_seq_generator",
        sequenceName = "condition_seq",
        initialValue = 1,
        allocationSize = 50
)
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_seq_generator")
    private Long id;

    private DiscountPolicy policy;

    abstract public void load();
}
