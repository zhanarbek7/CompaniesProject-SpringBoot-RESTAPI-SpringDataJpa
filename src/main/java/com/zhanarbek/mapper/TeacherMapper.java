package com.zhanarbek.mapper;

import com.zhanarbek.dto.TeacherDTO;
import com.zhanarbek.model.Teacher;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 4/4/22
 */
//This interfaces will be automatically implemented by library
@Mapper(componentModel = "spring")
public interface TeacherMapper {
    //Converting dto to entity
    Teacher dtoToEntity(TeacherDTO dto);

    //Converting entity to dto
    TeacherDTO entityToDto(Teacher teacher);

    //Converting list to dto list
    List<TeacherDTO> entityListToDtoList(List<Teacher> teachers);

    List<Teacher> dtoListToEntityList(List<TeacherDTO> dtoTeachers);
}
