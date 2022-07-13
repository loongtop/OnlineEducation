package com.gkhy.eduservice.entity;

import java.io.Serializable;

import com.gkhy.servicebase.DateModel;
import lombok.*;

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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Edu_Teacher")
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
    @Column(name = "id", unique = true, nullable = false, length = 19)
    private Long id;

    @Size(min = 4, max = 64, message = "Minimum username length: 4 characters")
    private String name;

    private String intro;

    private String career;

    private Integer level;

    private String avatar;

    private Integer sort;

    private Boolean isDeleted;

    public EduTeacher(Long id, String name, String intro) {
        this.id = id;
        this.name = name;
        this.intro = intro;
    }
}
