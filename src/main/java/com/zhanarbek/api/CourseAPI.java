package com.zhanarbek.api;

/*
 * Author: Zhanarbek Abdurasulov
 * Date: 26/3/22
 */

import com.zhanarbek.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import com.zhanarbek.dto.CourseDTO;
import com.zhanarbek.exception.NotFoundException;
import com.zhanarbek.model.Course;
import com.zhanarbek.response.Response;
import com.zhanarbek.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company/course")
public class CourseAPI {
    private final CourseService courseService;

    @GetMapping("/getCourse")
    public List<CourseDTO> getAllCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping("/get/{courseId}")
    public CourseDTO getCourseById(@PathVariable("courseId") Long courseId){
        return courseService.getCourseById(courseId);
    }

    @PostMapping("/save/{companyId}")
    public Response saveCourse(@RequestBody CourseDTO course,
                               @PathVariable("companyId") Long companyId){
        return courseService.saveCourse(course, companyId);
    }

    @PatchMapping("/update/{id}")
    public Response updateCourse(@RequestBody Course course, @PathVariable("id") Long id){
        return courseService.updateCourseById(id, course);
    }

    @DeleteMapping("delete/{courseId}")
    public Response deleteCourseById(@PathVariable("courseId") Long courseId){
        return courseService.deleteCourseByID(courseId);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException, @PathVariable String id) {
        return Response.builder()
                .httpStatus(BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleNotFoundException(NotFoundException notFoundException) {
        return Response.builder()
                .httpStatus(NOT_FOUND)
                .message(notFoundException.getMessage())
                .build();
    }


}
