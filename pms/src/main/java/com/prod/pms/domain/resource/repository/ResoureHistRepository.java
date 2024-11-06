package com.prod.pms.domain.resource.repository;

import com.prod.pms.domain.resource.entity.ResourceHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResoureHistRepository extends JpaRepository<ResourceHist, Integer> {
}
