package com.zhanarbek.service;

import com.zhanarbek.dto.GroupDTO;
import com.zhanarbek.model.Group;
import com.zhanarbek.response.Response;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 2/4/22
 */
public interface GroupService {
    Response saveGroup(GroupDTO group, Long companyId);

    void doesGroupExist(Group group);

    List<GroupDTO> findAllGroups();

    GroupDTO getGroupById (Long id);

    @Transactional
    Response updateGroupById(Long id, Group newGroup);

    Response deleteGroupById(Long id);
}
