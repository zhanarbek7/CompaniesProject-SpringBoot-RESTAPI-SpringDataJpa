package com.zhanarbek.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 3/4/22
 */
@Getter
@Setter
public class GroupDTO {
    private Long id;
    private String groupName;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;
    private List<Integer> coursesChoice;

}
