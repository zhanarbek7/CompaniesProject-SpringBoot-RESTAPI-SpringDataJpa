package com.zhanarbek.mapper;

import com.zhanarbek.dto.GroupDTO;
import com.zhanarbek.model.Group;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 4/4/22
 */
@Mapper(componentModel = "spring")
public interface GroupMapper {
    //Converting dto to entity
    Group dtoToEntity(GroupDTO dto);

    //Converting entity to dto
    GroupDTO entityToDto(Group group);

    //Converting list to dto list
    List<GroupDTO> entityListToDtoList(List<Group> groups);

    List<Group> dtoListToEntityList(List<GroupDTO> dtoGroups);
}
