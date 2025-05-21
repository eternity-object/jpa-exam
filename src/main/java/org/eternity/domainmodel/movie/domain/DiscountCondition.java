package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorColumn(name = "CONDITION_TYPE")
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_sequence")
    @SequenceGenerator(name = "condition_sequence", sequenceName = "condition_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private DiscountPolicy policy;

    abstract public void load();
}
