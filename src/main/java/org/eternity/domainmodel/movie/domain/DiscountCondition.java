package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "condition_type")
@SequenceGenerator(name = "condition_sequence")
@Entity
@Getter
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POLICY_ID")
    private DiscountPolicy policy;

    abstract public void load();
}
