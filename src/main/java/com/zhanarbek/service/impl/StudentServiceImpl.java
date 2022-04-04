package com.zhanarbek.service.impl;

import com.zhanarbek.exception.BadRequestException;
import com.zhanarbek.exception.NotFoundException;
import com.zhanarbek.model.Student;
import com.zhanarbek.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhanarbek.model.enums.StudyFormat;
import com.zhanarbek.repository.GroupRepository;
import com.zhanarbek.repository.StudentRepository;
import com.zhanarbek.service.StudentService;
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
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public Response saveStudent(Student student, Long groupId) {
        doesStudentExist(student.getEmail());
        student.setGroup(groupRepository.getById(groupId));
        try {
            studentRepository.save(student);
        }
        catch(Exception e){
            throw new NotFoundException(String.format("There is no group with id: %s", groupId));
        }
        return Response.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(String.format("Student with name %s, successfully created ", student.getFirstName()))
                .build();
    }

    @Override
    public void doesStudentExist(String studentEmail) {
        if(studentRepository.existsStudentByEmail(studentEmail)){
            log.warn("There is already a student with email: {}",
                    studentEmail);
            throw new BadRequestException(("There is already a student with email: {} " +
                    studentEmail+
                    "-> already exists"));
        }
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student with id = {} does not exist", id);
                    throw new NotFoundException(
                            String.format("Student with id = %s does not exist", id)
                    );
                });
        log.info("Founded Student with id = {}", id);
        return student;    }

    @Override
    @Transactional
    public Response updateStudentById(Long id, Student newStudent) {
        Student student = studentRepository.getById(id);

        String studentFirstName = student.getFirstName();
        String newStudentFirstName = newStudent.getFirstName();
        if (!Objects.equals(studentFirstName, newStudentFirstName)) {
            student.setFirstName(newStudentFirstName);
            log.info("Student with id = {} changed first name from {} to {}",
                    id, studentFirstName, newStudentFirstName);
        }

        String studentLastName = student.getLastName();
        String newStudentLastName = newStudent.getLastName();
        if (!Objects.equals(studentLastName, newStudentLastName)) {
            student.setLastName(newStudentFirstName);
            log.info("Student with id = {} changed last name from {} to {}",
                    id, studentLastName, newStudentLastName);
        }

        String email = student.getEmail();
        String newEmail = newStudent.getEmail();
        if (!Objects.equals(email, newEmail)) {
            student.setEmail(newEmail);
            log.info("Teacher with id = {} changed email from {} to {}",
                    id, email, newEmail);
        }

        StudyFormat studyFormat = student.getStudyFormat();
        StudyFormat newStudyFormat = newStudent.getStudyFormat();
        if (!Objects.equals(studyFormat, newStudyFormat)) {
            student.setStudyFormat(newStudyFormat);
            log.info("Student with id = {} changed studyFormat from {} to {}",
                    id, studyFormat.toString(), newStudyFormat.toString());
        }

        String message = String.format("Student with id = %s has successfully been updated", id);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }

    @Override
    public Response deleteStudentById(Long id) {
        try {
            studentRepository.deleteById(id);
        }
        catch (Exception e){
            throw new NotFoundException(String.format("Student with id %s has not been found!" , id));
        }
        log.info("Student with id = {} has successfully been deleted", id);
        String message = String.format("Student with id = %s has successfully been deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }
}
