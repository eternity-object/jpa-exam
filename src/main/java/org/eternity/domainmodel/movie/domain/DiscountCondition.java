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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONDITION_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id") // FK 컬럼을 알고있는 주인
    private DiscountPolicy policy;

    abstract public void load();
}
