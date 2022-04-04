package com.zhanarbek.dto;

import lombok.Getter;
import lombok.Setter;
import com.zhanarbek.model.enums.StudyFormat;

/**
 * Author: Zhanarbek Abdurasulov
 * Date: 3/4/22
 */
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
}
