package com.zhanarbek.service;

import com.zhanarbek.dto.CourseDTO;
import com.zhanarbek.model.Course;
import com.zhanarbek.response.Response;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 26/3/22
 */
public interface CourseService {

    Response saveCourse(CourseDTO course, Long companyId);

    void doesCourseExist(CourseDTO courseDTO);

    List<CourseDTO> findAllCourses();

    CourseDTO getCourseById (Long id);

    @Transactional
    Response updateCourseById(Long id, Course newCourse);

    Response deleteCourseByID(Long id);

}
