package com.ch.jonasgredig.codingchallenge.model.repository;

import com.ch.jonasgredig.codingchallenge.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Override
    Optional<Customer> findById(Integer integer);

    @Override
    <S extends Customer> S save(S entity);
}
