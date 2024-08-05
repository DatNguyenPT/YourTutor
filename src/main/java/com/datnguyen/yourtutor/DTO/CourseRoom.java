package com.datnguyen.yourtutor.DTO;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="courseroom")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomid;

    @ManyToOne
    @JoinColumn(name = "courseid")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "studentid")
    private Student student;

}
