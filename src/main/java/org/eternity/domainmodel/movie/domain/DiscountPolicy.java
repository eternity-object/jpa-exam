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

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DiscountCondition> conditions = new HashSet<>();

    public DiscountPolicy(DiscountCondition ... conditions) {
        for (DiscountCondition condition : conditions) {
            this.addCondition(condition);
        }
    }

    private void addCondition(DiscountCondition condition) {
        this.conditions.add(condition);
        condition.setPolicy(this);
    }

    public void load() {
        conditions.stream().forEach(DiscountCondition::load);
    }
}
