package com.gkhy.eduservice.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.servicebase.DateModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * <p>
 * teacher
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */

@Accessors(chain = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class EduTeacher extends DateModel implements Serializable {

    private static final long serialVersionUID = -4157044038035512717L;

    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    @Column(unique = true, nullable = false, length = 19)
    private Long id;

    @Size(min = 4, max = 64, message = "Minimum username length: 4 characters")
    private String name;

    private String intro;

    private String career;

    private Integer level;

    private String avatar;

    private Integer sort;

    private Boolean isDeleted;

}
