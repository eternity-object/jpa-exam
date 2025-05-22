package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Cascade;

@Entity
@Getter
@SequenceGenerator(
        name = "condition_sequence",
        sequenceName = "condition_sequence",
        initialValue = 1, allocationSize = 50
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "condition_type")
public abstract class DiscountCondition {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "POLICY_ID")
    private DiscountPolicy policy;

    abstract public void load();
}
