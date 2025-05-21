package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONDITION_TYPE")
@SequenceGenerator(
        name = "discount_condition_seq_generator",
        sequenceName = "condition_sequence"
)
public abstract class DiscountCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "discount_condition_seq_generator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POLICY_ID")
    private DiscountPolicy policy;

    abstract public void load();
}
