package com.zhanarbek.mapper;

import com.zhanarbek.dto.CourseDTO;
import com.zhanarbek.model.Course;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 3/4/22
 */
//This interfaces will be automatically implemented by library
@Mapper(componentModel = "spring")
public interface CourseMapper {
    //Converting dto to entity
    Course dtoToEntity(CourseDTO dto);

    //Converting entity to dto
    CourseDTO entityToDto(Course course);

    //Converting list to dto list
    List<CourseDTO> entityListToDtoList(List<Course> courses);

    List<Course> dtoListToEntityList(List<CourseDTO> dtoCourses);
}
