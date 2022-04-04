package com.zhanarbek.service;

import com.zhanarbek.model.Student;
import com.zhanarbek.response.Response;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 2/4/22
 */
public interface StudentService {
    Response saveStudent(Student student, Long groupId);

    void doesStudentExist(String studentEmail);

    List<Student> findAllStudents();

    Student getStudentById (Long id);

    @Transactional
    Response updateStudentById(Long id, Student newStudent);

    Response deleteStudentById(Long id);
}
