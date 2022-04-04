package com.zhanarbek.service.impl;

import com.zhanarbek.exception.BadRequestException;
import com.zhanarbek.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhanarbek.dto.CourseDTO;
import com.zhanarbek.exception.NotFoundException;
import com.zhanarbek.mapper.CourseMapper;
import com.zhanarbek.model.Course;
import com.zhanarbek.repository.CompanyRepository;
import com.zhanarbek.repository.CourseRepository;
import com.zhanarbek.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.RESET_CONTENT;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 26/3/22
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final CourseMapper courseMapper;

    @Override
    public Response saveCourse(CourseDTO courseDTO, Long companyId) {
        doesCourseExist(courseDTO);
        Course course = courseMapper.dtoToEntity(courseDTO);
        course.setCompany(companyRepository.getById(companyId));
        try{
            courseRepository.save(course);
        }
        catch (Exception e){
            throw new NotFoundException(String.format("There is no company with id: %s", companyId));
        }
        return Response.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(String.format("Course with name %s, successfully created ", course.getCourseName()))
                .build();
    }

    @Override
    public void doesCourseExist(CourseDTO courseDTO) {
        if(courseRepository.existsByCourseName(courseDTO.getCourseName())){
            log.warn("course with courseName: {} -> already exists",
                    courseDTO.getCourseName());
            throw new BadRequestException(("course with courseName: " +courseDTO.getCourseName()+
                    "-> already exists"));
        }
    }

    @Override
    public List<CourseDTO> findAllCourses() {
        return courseMapper.entityListToDtoList(courseRepository.findAll());
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Course with id = {} does not exist", id);
                    throw new NotFoundException(
                            String.format("Course with id = %s does not exist", id)
                    );
                });
        log.info("Founded Course with id = {}", id);
        return courseMapper.entityToDto(course);
    }

    @Override
    @Transactional
    public Response updateCourseById(Long id, Course newCourse) {
        Course course = courseRepository.getById(id);

        String courseName = course.getCourseName();
        String newCourseName = newCourse.getCourseName();
        if (!Objects.equals(courseName, newCourseName)) {
            course.setCourseName(newCourseName);
            log.info("Course with id = {} changed name from {} to {}",
                    id, courseName, newCourseName);
        }

        String duration = course.getDuration();
        String newDuration = newCourse.getDuration();
        if (!Objects.equals(duration, newDuration)) {
            course.setDuration(newDuration);
            log.info("Course with id = {} changed duration from {} to {}",
                    id, duration, newDuration);
        }


        String message = String.format("Course with companyId = %s has successfully updated", id);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }

    @Override
    public Response deleteCourseByID(Long id) {
        try {
            courseRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new NotFoundException(String.format("Course with id %s has not been found!", id));
        }
        log.info("Course with id = {} has successfully been deleted", id);
        String message = String.format("Course with id = %s has successfully been deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }


}
