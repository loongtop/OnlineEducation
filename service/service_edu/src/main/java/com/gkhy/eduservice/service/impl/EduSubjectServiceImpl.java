package com.gkhy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.gkhy.eduservice.entity.EduSubject;
import com.gkhy.eduservice.entity.excel.SubjectData;
import com.gkhy.eduservice.entity.subject.OneSubject;
import com.gkhy.eduservice.listener4Excel.SubjectExcelListener;
import com.gkhy.eduservice.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class EduSubjectServiceImpl implements EduSubjectService {
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //file input stream
            InputStream in = file.getInputStream();
            //call method to read
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        return null;
    }

    @Override
    public void save(EduSubject existOneSubject) {

    }
}
