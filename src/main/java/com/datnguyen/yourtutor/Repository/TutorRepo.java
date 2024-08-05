package com.datnguyen.yourtutor.Repository;

import com.datnguyen.yourtutor.DTO.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepo extends JpaRepository<Tutor, Long> {
}
