package com.gkhy.eduservice;

import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.service.impl.EduTeacherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class EduApplicationTests {

    @Autowired
    private EduTeacherServiceImpl eduTeacherService;

    @Test
    void contextLoads() {
        List<EduTeacher> eduTeacherList =  eduTeacherService.list();
        System.out.println(eduTeacherList);
    }

}