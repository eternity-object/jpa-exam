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
        // 0 - 0, 1
        // 1 - 1, 2
        // 2 - 2, 3
        // 3 - 3, 4
        IntStream.range(0, 4)
                .forEach(index -> em.persist(new DiscountPolicy(new SequenceCondition(index), new SequenceCondition(index+1))));

        em.flush();
        em.clear();

        // SequenceCondition 지연로딩
        List<DiscountPolicy> policies = em.createQuery("select p from DiscountPolicy p").getResultList();

        for(DiscountPolicy each : policies) {
            each.load();
        }
    }
}
