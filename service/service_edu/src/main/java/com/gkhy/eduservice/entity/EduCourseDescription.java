package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.DateModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class EduCourseDescription extends DateModel implements Serializable {

    private static final long serialVersionUID = 730107770905520088L;

    private String id;

    private String description;

}
