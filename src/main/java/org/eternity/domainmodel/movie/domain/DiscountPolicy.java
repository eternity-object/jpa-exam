package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter
public class DiscountPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "policy", fetch = FetchType.LAZY)
    @org.hibernate.annotations.BatchSize(size = 2)
    private Set<DiscountCondition> conditions = new HashSet<>();

    public DiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Set.of(conditions);
    }

    public void load() {
        conditions.stream().forEach(DiscountCondition::load);
    }
}
