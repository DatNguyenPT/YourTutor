package com.datnguyen.yourtutor.DTO;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name="tutor", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tutor extends UserManagement{
    private Float rating;
    private String subjects;
    @Column(name = "yearexp")
    private Integer yearExp;

    @OneToMany(mappedBy = "tutor")
    List<Course>courses;
}
