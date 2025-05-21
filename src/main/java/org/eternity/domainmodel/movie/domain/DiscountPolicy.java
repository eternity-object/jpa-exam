package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="policy_type")
@Entity
@NoArgsConstructor @Getter
public class DiscountPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<DiscountCondition> conditions = new HashSet<>();

    public DiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Set.of(conditions);
    }

    public void load() {
        conditions.stream().forEach(DiscountCondition::load);
    }
}
