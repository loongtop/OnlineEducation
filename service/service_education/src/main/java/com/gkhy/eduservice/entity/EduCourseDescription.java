package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.DateModel;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * Course
 * </p>
 *
 * @author leo
 * @since 2022-07-13
 */

@Accessors(chain = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class EduCourseDescription extends DateModel implements Serializable {

    private static final long serialVersionUID = 730107770905520088L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CourseDescription_sequence"
    )
    private String id;

    private String description;

}
