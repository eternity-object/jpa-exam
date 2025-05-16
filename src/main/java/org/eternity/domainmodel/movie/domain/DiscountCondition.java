package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public abstract class DiscountCondition {
    private Long id;

    private DiscountPolicy policy;

    abstract public void load();
}
