package org.eternity.domainmodel.movie.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountPolicyRepository extends JpaRepository<DiscountPolicy, Long> {

    @EntityGraph(attributePaths = "conditions")
    List<DiscountPolicy> findAll();
}
