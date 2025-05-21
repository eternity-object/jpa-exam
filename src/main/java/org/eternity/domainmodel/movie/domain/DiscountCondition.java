package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@SequenceGenerator(
        name = "discount_condition_id_seq",
        sequenceName = "condition_sequence",
        initialValue = 1, allocationSize = 1
)
@Getter
public abstract class DiscountCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_condition_id_seq")
    private Long id;

    @ManyToOne
    private DiscountPolicy policy;

    abstract public void load();
}
