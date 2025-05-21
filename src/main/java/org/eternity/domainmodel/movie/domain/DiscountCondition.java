package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@SequenceGenerator(
        name = "discount_seq_generator",
        sequenceName = "condition_seq",
        initialValue = 1,
        allocationSize = 50
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CONDITION_TYPE")
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_seq_generator")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private DiscountPolicy policy;

    abstract public void load();
}
