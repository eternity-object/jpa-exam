package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SequenceGenerator(
        name = "condition_sequence",
        sequenceName = "condition_sequence",
        allocationSize = 1
)
@DiscriminatorColumn(name = "CONDITION_TYPE")
public abstract class DiscountCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POLICY_ID")
    private DiscountPolicy discountPolicy;

    abstract public void load();
}
