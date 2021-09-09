package com.disributors.API.repositories;


import java.util.List;

import com.disributors.API.models.Student;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface StudentRepository extends CrudRepository<Student, Integer> {
    List<Student> findAll(Pageable pageable);
}
