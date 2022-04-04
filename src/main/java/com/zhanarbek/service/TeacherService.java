package com.zhanarbek.service;

import com.zhanarbek.dto.TeacherDTO;
import com.zhanarbek.model.Course;
import com.zhanarbek.response.Response;
import com.zhanarbek.model.Teacher;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 2/4/22
 */
public interface TeacherService {

    Response saveTeacher(Long courseId, TeacherDTO teacher);

    void doesTeacherExist(Course course);

    List<TeacherDTO> findAllTeacher();

    TeacherDTO getTeacherById (Long id);

    @Transactional
    Response updateTeacherById(Long id, Teacher newTeacher);

    Response deleteTeacherById(Long id);

}
