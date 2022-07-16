package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Course
 * </p>
 *
 * @author leo
 * @since 2022-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EduCourse extends DateModel implements Serializable {

    private static final long serialVersionUID = 1581724162696724325L;

    private String id;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private Long version;

    private String status;

    private Integer isDeleted;
}
