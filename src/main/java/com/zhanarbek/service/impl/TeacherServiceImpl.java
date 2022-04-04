package com.zhanarbek.service.impl;

import com.zhanarbek.exception.BadRequestException;
import com.zhanarbek.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhanarbek.dto.TeacherDTO;
import com.zhanarbek.exception.NotFoundException;
import com.zhanarbek.mapper.TeacherMapper;
import com.zhanarbek.model.Course;
import com.zhanarbek.model.Teacher;
import com.zhanarbek.repository.CourseRepository;
import com.zhanarbek.repository.TeacherRepository;
import com.zhanarbek.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.RESET_CONTENT;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 2/4/22
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public Response saveTeacher(Long courseId, TeacherDTO teacherDTO) {
        doesTeacherExist(courseRepository.getById(courseId));
        Teacher teacher = teacherMapper.dtoToEntity(teacherDTO);
        teacher.setCourse(courseRepository.getById(courseId));
        try{
            teacherRepository.save(teacher);
        }
        catch (Exception e){
            throw new NotFoundException(String.format("There is no course with id: %s", courseId));
        }
        return Response.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(String.format("Teacher with name %s, successfully created ", teacher.getFirstName()))
                .build();
    }

    @Override
    public void doesTeacherExist(Course course) {
        if(teacherRepository.existsByCountOfTeachersInCourse(course)){
            log.warn("There is already a teacher with courseId: {}",
                    course);
            throw new BadRequestException(("Teacher with courseId: {} " +
                    course+
                    "-> already exists"));
        }
    }

    @Override
    public List<TeacherDTO> findAllTeacher() {
        return teacherMapper.entityListToDtoList(teacherRepository.findAll());
    }

    @Override
    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Teacher with id = {} does not exist", id);
                    throw new NotFoundException(
                            String.format("Teacher with id = %s does not exist", id)
                    );
                });
        log.info("Founded Teacher with id = {}", id);
        return teacherMapper.entityToDto(teacher);
    }

    @Override
    @Transactional
    public Response updateTeacherById(Long teacherId, Teacher newTeacher) {
        Teacher teacher = teacherRepository.getById(teacherId);

        String teacherFirstName = teacher.getFirstName();
        String newTeacherFirstName = newTeacher.getFirstName();
        if (!Objects.equals(teacherFirstName, newTeacherFirstName)) {
            teacher.setFirstName(newTeacherFirstName);
            log.info("Teacher with id = {} changed first name from {} to {}",
                    teacherId, teacherFirstName, newTeacherFirstName);
        }

        String teacherLastName = teacher.getLastName();
        String newTeacherLastName = newTeacher.getLastName();
        if (!Objects.equals(teacherLastName, newTeacherLastName)) {
            teacher.setLastName(newTeacherLastName);
            log.info("Teacher with id = {} changed last name from {} to {}",
                    teacherId, teacherLastName, newTeacherLastName);
        }

        String email = teacher.getEmail();
        String newEmail = newTeacher.getEmail();
        if (!Objects.equals(email, newEmail)) {
            teacher.setEmail(newEmail);
            log.info("Teacher with id = {} changed email from {} to {}",
                    teacherId, email, newEmail);
        }

        String message = String.format("Teacher with teacherID = %s has successfully been updated", teacherId);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }

    @Override
    public Response deleteTeacherById(Long id) {
        try {
            teacherRepository.deleteById(id);
        }
        catch (Exception e){
            throw new NotFoundException(String.format("Teacher with id %s has not been found!", id));
        }
        log.info("Teacher with id = {} has successfully deleted", id);
        String message = String.format("Teacher with id = %s has successfully deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }
}
