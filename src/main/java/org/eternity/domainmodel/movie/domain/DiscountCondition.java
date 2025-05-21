package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SequenceGenerator(
        name = "condition_sequence",
        sequenceName = "condition_sequence",
        initialValue = 1, allocationSize = 50
)
@DiscriminatorColumn(name = "condition_type")
public abstract class DiscountCondition {

    @Id
    @GeneratedValue(generator = "condition_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    @Setter
    private DiscountPolicy policy;

    abstract public void load();
}
