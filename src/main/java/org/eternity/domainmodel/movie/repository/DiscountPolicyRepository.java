package org.eternity.domainmodel.movie.repository;

import org.eternity.domainmodel.movie.domain.DiscountPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscountPolicyRepository extends JpaRepository<DiscountPolicy, Long> {

    // DiscountPolicy와 연관된 DiscountCondition을 함께 조회하는 페치 조인 쿼리
    @Query("SELECT dp FROM DiscountPolicy dp LEFT JOIN FETCH dp.conditions")
    List<DiscountPolicy> findAllWithConditions();
}