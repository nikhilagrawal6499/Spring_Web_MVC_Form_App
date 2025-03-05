package com.nikhilit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhilit.entities.StudentEntity;
import com.nikhilit.models.Student;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
