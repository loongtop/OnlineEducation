package com.gkhy.eduservice.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherQuery {

    private String name;

    private Integer level;

    private String begin;

    private String end;
}
