package com.gkhy.eduorder.repository;

import com.gkhy.eduorder.entity.PayLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PayLogRepository extends
        JpaSpecificationExecutor<PayLog>,
        JpaRepository<PayLog, String> {
}
