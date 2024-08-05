package com.datnguyen.yourtutor.Repository;

import com.datnguyen.yourtutor.DTO.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
}
