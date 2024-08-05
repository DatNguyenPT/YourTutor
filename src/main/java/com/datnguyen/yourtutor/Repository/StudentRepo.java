package com.datnguyen.yourtutor.Repository;

import com.datnguyen.yourtutor.DTO.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}
