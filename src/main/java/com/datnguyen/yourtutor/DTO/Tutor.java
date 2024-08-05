package com.datnguyen.yourtutor.DTO;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tutor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dob;

    private LocalDate joineddate;

    private Float rating;

    private String phonenum;

    private String subjects;

    private Integer yearexp;

    @OneToMany(mappedBy = "tutor")
    List<Course>courses;
}
