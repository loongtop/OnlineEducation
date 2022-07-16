package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * Course subjects
 * </p>
 *
 * @author leo
 * @since 2022-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EduSubject extends DateModel implements Serializable {

    private static final long serialVersionUID = 366835828919658691L;

    private String id;

    private String title;

    private String parentId;

    private Integer sort;

}
