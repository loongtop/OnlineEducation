package com.gkhy.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//primary classification
@Data
public class OneSubject {
    private String id;
    private String title;

    //A primary category has multiple secondary categories
    private List<TwoSubject> children = new ArrayList<>();
}
