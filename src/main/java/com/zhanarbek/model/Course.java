package com.zhanarbek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 26/3/22
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "courses")
public class Course {
    @Id
    @SequenceGenerator(
            name = "companies_sequence",
            sequenceName = "companies_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "companies_sequence"
    )
    private Long id;
    private String courseName;
    private String duration;
    @JsonIgnore
    @ManyToOne
    private Company company;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "courses", cascade = {CascadeType.MERGE, CascadeType.REMOVE} )
    private List<Group> groups;

    @OneToOne(mappedBy = "course", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Teacher teacher;

}
