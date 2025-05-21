package org.eternity.domainmodel;

import jakarta.persistence.EntityManager;
import org.eternity.domainmodel.movie.domain.DiscountPolicy;
import org.eternity.domainmodel.movie.domain.SequenceCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.IntStream;

@DataJpaTest(showSql = false)
public class DiscountPolicyTest {
    @Autowired
    private EntityManager em;

    @Test
    public void lazy_load() {
        IntStream.range(0, 4)
                .forEach(index -> em.persist(new DiscountPolicy(new SequenceCondition(index), new SequenceCondition(index+1))));

        em.flush();
        em.clear();

        List<DiscountPolicy> policies = em.createQuery("select p from DiscountPolicy p").getResultList();
        // N+1 문제 해결을 위한 -> 페치 조인 사용( 주석처리 )
        // List<DiscountPolicy> policies = em.createQuery("select p from DiscountPolicy p left join fetch p.conditions", DiscountPolicy.class).getResultList();
        for(DiscountPolicy each : policies) {
            each.load();
        }
    }
}