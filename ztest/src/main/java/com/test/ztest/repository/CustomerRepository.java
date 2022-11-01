package com.test.ztest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.ztest.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
