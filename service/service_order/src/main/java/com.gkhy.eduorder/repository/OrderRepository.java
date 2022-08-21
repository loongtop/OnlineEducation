package com.gkhy.eduorder.repository;

import com.gkhy.eduorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends
        JpaSpecificationExecutor<Order>,
        JpaRepository<Order, String>  {
}