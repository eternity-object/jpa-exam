package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter
public class DiscountPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "POLICY_ID")
    @Fetch(FetchMode.SUBSELECT)
    private Set<DiscountCondition> conditions = new HashSet<>();

    public DiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Set.of(conditions);
    }

    public void load() {
        conditions.stream().forEach(DiscountCondition::load);
    }
}
