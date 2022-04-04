package com.zhanarbek.repository;

import com.zhanarbek.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 2/4/22
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select case when count(s) > 0 then true else false end " +
            "from Student s where s.email = ?1")
    boolean existsStudentByEmail(String email);
}
