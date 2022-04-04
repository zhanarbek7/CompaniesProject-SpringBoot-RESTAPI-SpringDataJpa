package com.zhanarbek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 25/2/22
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "groups")
public class Group {
    @Id
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    private Long id;
    @Column(name = "group_name")
    private String groupName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfFinish;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Course> courses;

    @OneToMany(mappedBy="group", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Student> students;

    @Transient
    private List<Integer> coursesChoice;

    @ManyToOne
    @JsonIgnore
    private Company company;


}
