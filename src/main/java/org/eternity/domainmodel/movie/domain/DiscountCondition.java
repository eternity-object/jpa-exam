package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@SequenceGenerator(
    name = "CONDITION_SEQ_GENERATOR",
    sequenceName = "condition_sequence",
    initialValue = 1,
    allocationSize = 1
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONDITION_TYPE")
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONDITION_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private DiscountPolicy policy;

    public void setPolicy(DiscountPolicy policy) {
        this.policy = policy;
    }

    abstract public void load();
}
