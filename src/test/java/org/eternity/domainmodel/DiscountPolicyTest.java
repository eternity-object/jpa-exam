package org.eternity.domainmodel;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.eternity.domainmodel.movie.domain.DiscountPolicy;
import org.eternity.domainmodel.movie.domain.SequenceCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest(showSql = false)
public class DiscountPolicyTest {
    @Autowired
    private EntityManager em;

    @DisplayName("DiscountPolicy에 id가 IDENTIFY로 설정되어 있는지 확인한다.")
    @Test
    void DiscountPolicy_id_is_IDENTIFY(){
        DiscountPolicy discountPolicy = new DiscountPolicy(new SequenceCondition(1), new SequenceCondition(2));

        em.persist(discountPolicy);

        em.flush();
        em.clear();

        DiscountPolicy loaded = em.find(DiscountPolicy.class, discountPolicy.getId());

        assertThat(loaded).isNotNull();
        assertThat(loaded.getId()).isEqualTo(discountPolicy.getId());

    }

    @Test
    public void lazy_load() {
        IntStream.range(0, 4)
                .forEach(index -> em.persist(new DiscountPolicy(new SequenceCondition(index), new SequenceCondition(index+1))));

        em.flush();
        em.clear();

        List<DiscountPolicy> policies = em.createQuery("select p from DiscountPolicy p").getResultList();

        for(DiscountPolicy each : policies) {
            each.load();
        }
    }
}
