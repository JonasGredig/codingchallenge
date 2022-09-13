package com.ch.jonasgredig.codingchallenge.model.repository;

import com.ch.jonasgredig.codingchallenge.model.Dialog;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DialogRepository extends JpaRepository<Dialog, Integer> {


    @Override
    <S extends Dialog> S save(S entity);

    @Override
    void deleteById(Integer integer);

    @Override
    Page<Dialog> findAll(Example criteria, Pageable pageable);

    @Override
    <S extends Dialog> List<S> findAll(Example<S> example);
}
