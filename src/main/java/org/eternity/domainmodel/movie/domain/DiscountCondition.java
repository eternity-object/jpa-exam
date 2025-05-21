package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name             = "CONDITION_TYPE",
        discriminatorType= DiscriminatorType.STRING
)
@SequenceGenerator(
        name = "condition_seq",
        sequenceName = "condition_seq",
        initialValue = 1, allocationSize = 50
)
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "condition_seq"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private DiscountPolicy policy;

    abstract public void load();
}
