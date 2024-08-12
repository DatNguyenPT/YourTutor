package com.datnguyen.yourtutor.DTO;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "course", schema = "public")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String tutorname;
    private Long slot;
    private LocalDate startdate;
    private LocalDate enddate;

    @ManyToOne
    @JoinColumn(name = "tutorid")
    private Tutor tutor;

    @OneToMany(mappedBy = "course")
    private List<CourseRoom> courseRooms;
}
