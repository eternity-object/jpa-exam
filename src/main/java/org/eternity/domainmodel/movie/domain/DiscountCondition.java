package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@DiscriminatorColumn(name = "CONDITION_TYPE")
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POLICY_ID")
    private DiscountPolicy policy;

    abstract public void load();
}
