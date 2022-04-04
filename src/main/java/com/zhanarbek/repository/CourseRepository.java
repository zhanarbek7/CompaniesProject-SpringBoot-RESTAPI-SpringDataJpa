package com.zhanarbek.repository;

import com.zhanarbek.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 26/3/22
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select case when count(s) > 0 then true else false end " +
            "from Course s where s.courseName = ?1")
    boolean existsByCourseName(String courseName);
}
