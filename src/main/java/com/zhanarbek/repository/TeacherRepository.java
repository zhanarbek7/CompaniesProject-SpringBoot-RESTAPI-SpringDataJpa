package com.zhanarbek.repository;

import com.zhanarbek.model.Course;
import com.zhanarbek.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 2/4/22
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select case when count(s) > 0 then true else false end " +
            "from Teacher s where s.course = ?1")
    boolean existsByCountOfTeachersInCourse(Course course);

}
