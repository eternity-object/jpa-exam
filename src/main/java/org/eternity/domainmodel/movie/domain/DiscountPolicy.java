package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DiscountPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "policy", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<DiscountCondition> conditions = new HashSet<>();

    public DiscountPolicy(DiscountCondition... conditions) {
        this.conditions = Set.of(conditions);
    }

    public void addDiscountPolicy(DiscountCondition condition) {
        this.conditions.add(condition);
    }

    public void load() {
        conditions.stream().forEach(DiscountCondition::load);
    }
}
