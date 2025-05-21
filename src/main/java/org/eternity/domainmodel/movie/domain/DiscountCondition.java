package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
// DiscountCondition 상속 계층 전체를 하나의 테이블에 매핑
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 타입을 저장할 컬럼의 이름은 "CONDITION_TYPE"으로 지정
@DiscriminatorColumn(name = "CONDITION_TYPE")
@Getter
public abstract class DiscountCondition {
    @Id
    // DiscountCondition 상속 계층의 Id는 SEQUENCE로, DB 시퀀스 이름은 "condition_sequence"
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "condition_sequence_generator")
    @SequenceGenerator(name = "condition_sequence_generator",
            sequenceName = "condition_sequence",
            allocationSize = 1)
    private Long id;

    // 양방향 연관관계, 지연로딩
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_policy_id")
    @Setter
    private DiscountPolicy policy;

    abstract public void load();
}