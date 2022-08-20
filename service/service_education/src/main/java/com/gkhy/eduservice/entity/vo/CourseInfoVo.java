package com.gkhy.eduservice.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {
    private String id;

    private String teacherId;

    private String subjectId;

    private String title;

    // 0.01
    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private String description;
}
