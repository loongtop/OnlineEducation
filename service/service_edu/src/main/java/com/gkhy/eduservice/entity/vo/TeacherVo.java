package com.gkhy.eduservice.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class TeacherVo {

    private String name;

    private Integer level;

    private Date begin;

    private Date end;
}
