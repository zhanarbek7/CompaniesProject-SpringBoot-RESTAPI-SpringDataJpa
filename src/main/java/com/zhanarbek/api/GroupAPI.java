package com.zhanarbek.api;

import com.zhanarbek.dto.GroupDTO;
import com.zhanarbek.exception.BadRequestException;
import com.zhanarbek.exception.NotFoundException;
import com.zhanarbek.response.Response;
import lombok.RequiredArgsConstructor;
import com.zhanarbek.model.Group;
import com.zhanarbek.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 2/4/22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company/group")
public class GroupAPI {

    private final GroupService groupService;

    @PostMapping("/save/{companyId}")
    public Response saveGroup(@PathVariable("companyId") Long companyId,
                              @RequestBody GroupDTO group){
        return groupService.saveGroup(group, companyId);
    }

    @GetMapping("/getGroup")
    public List<GroupDTO> getAllGroups(){
        return groupService.findAllGroups();
    }

    @GetMapping("/get/{groupId}")
    public GroupDTO getGroupById(@PathVariable("groupId") Long groupId){
        return groupService.getGroupById(groupId);
    }

    @DeleteMapping("/delete/{groupId}")
    public Response deleteGroupById(@PathVariable("groupId") Long groupId){
        return groupService.deleteGroupById(groupId);
    }

    @PatchMapping("/update/{groupId}")
    public Response updateGroupId(@PathVariable("groupId") Long groupId,
                                  @RequestBody Group group){
        return groupService.updateGroupById(groupId, group);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException) {
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
