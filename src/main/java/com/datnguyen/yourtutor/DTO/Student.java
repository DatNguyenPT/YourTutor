package com.datnguyen.yourtutor.DTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="student", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student extends UserManagement{
    private int grade;
}
