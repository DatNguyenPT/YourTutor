package com.datnguyen.yourtutor.Repository;

import com.datnguyen.yourtutor.DTO.CourseRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRoomRepo extends JpaRepository<CourseRoom, Long> {
}
