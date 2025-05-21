package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "condition_sequence"
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONDITION_TYPE")
public abstract class DiscountCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private DiscountPolicy policy;

    abstract public void load();
}
