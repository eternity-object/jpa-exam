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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 요구사항 1: DiscountPolicy 클래스의 Id는 IDENTITY 유형으로 정의
    private Long id;

    // 양방향 연관관계로 변경, 지연로딩, 영속성 전이
    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DiscountCondition> conditions = new HashSet<>();

    public DiscountPolicy(DiscountCondition ... conditions) {
        for (DiscountCondition condition : conditions) {
            addCondition(condition); // 양방향 관계 설정을 위해 메서드 사용
        }
    }

    public void addCondition(DiscountCondition condition) {
        this.conditions.add(condition);
        condition.setPolicy(this);
    }

    public void load() {
        conditions.stream().forEach(DiscountCondition::load);
    }
}