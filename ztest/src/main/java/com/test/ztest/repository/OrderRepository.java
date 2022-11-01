package com.test.ztest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.ztest.entity.OrdersByCustomer;

public interface OrderRepository extends JpaRepository<OrdersByCustomer, Long> {

}
