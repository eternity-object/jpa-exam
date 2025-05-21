JPA 기반의 애플리케이션 설계 실습 문제

## 구현 목표

다음 처럼 코드를 수정해서 테스트 케이스가 패스하도록 만드세요.

1. DiscountPolicy와 DiscountCondition에 Id를 추가하세요.
   DiscountPolicy 클래스의 Id는 IDENTITY 유형으로 정의하고, DiscountCondition 상속 계층의 Id는 SEQUENCE 유형으로 정의합니다.
   이때 DB 시퀀스의 이름은 “condition_sequence”로 지으세요.
```java
public class DiscountPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    ...
}

@SequenceGenerator(
        name = "discount_seq_generator",
        sequenceName = "condition_seq",
        initialValue = 1,
        allocationSize = 50
)
public abstract class DiscountCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_seq_generator")
    private Long id;
    ...
}
```

2. DiscountCondition 상속 계층 전체를 하나의 테이블에 매핑하세요.
   이때 타입을 저장할 컬럼의 이름은 “CONDITION_TYPE”으로, 컬럼에 저장할 값은 SequenceCondition의 경우에는 “SEQUENCE”로, PeriodCondition의 경우에는 “PERIOD”로 지정하세요.
```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="CONDITION_TYPE")
public abstract class DiscountCondition

@DiscriminatorValue("PERIOD")
public class PeriodCondition extends DiscountCondition

@DiscriminatorValue("SEQUENCE")
public class SequenceCondition extends DiscountCondition 
```
3. DiscountPolicy와 DiscountCondition은 단방향 연관관계를 양방향 연관관계로 변경하세요.
   이때 DiscountPolicy가 저장될 때 자동으로 DiscountCondition도 함께 저장될 수 있도록 설정하세요.
   DiscountPolicy와 DiscountCondition는 지연로딩되도록 설정하세요.
```java
public abstract class DiscountCondition {
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private DiscountPolicy policy;
    ...
}

public class DiscountPolicy {
    @OneToMany(mappedBy = "policy", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @BatchSize(size = 5)
    private Set<DiscountCondition> conditions = new HashSet<>();
    ...
}
```
4. 테스트 케이스를 보면 JPQL을 이용해서 DiscountPolicy의 목록을 조회한 후에 각 DiscountPolicy 별로 DiscountCondition에 접근하고 있습니다. 이로 인해 N+1 문제가 발생하는데 N+1 문제가 발생하지 않도록 수정하세요.
   이때 N+1문제를 해결하는 방법은 자유롭게 선택하시면 됩니다.
```java
public class DiscountPolicy {
    @OneToMany(mappedBy = "policy", fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    private Set<DiscountCondition> conditions = new HashSet<>();
    ...
}
```