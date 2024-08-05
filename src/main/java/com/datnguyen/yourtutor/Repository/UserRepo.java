package com.datnguyen.yourtutor.Repository;

import com.datnguyen.yourtutor.DTO.UserManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<UserManagement, Long> {
}
