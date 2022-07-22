package com.gkhy.eduservice.listener4Excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.gkhy.eduservice.entity.EduSubject;
import com.gkhy.eduservice.entity.excel.SubjectData;
import com.gkhy.eduservice.service.EduSubjectService;
import com.gkhy.servicebase.exceptionhandler.EducationException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //Because the SubjectExcelListener is not handed over to spring for management,
    // it needs to be new and cannot be injected into other objects
    //Cannot implement database operation
    public EduSubjectService eduSubjectService;
    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.eduSubjectService = subjectService;
    }

    //Read excel content, read line by line
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null) {
            throw new EducationException(20001,"file data is empty");
        }

        //Read line by line, there are two values for each read, the first value is first-level classification, and the second value is second-level classification
        //Determine whether the first-level classification is repeated
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if(existOneSubject == null) {  //if There is no same first-level classification, add it
            existOneSubject = new EduSubject();
            existOneSubject.setParentId(0L);
            existOneSubject.setTitle(subjectData.getOneSubjectName());//first class name
            eduSubjectService.save(existOneSubject);
        }

        //Get the first-level classification id value
        Long pid = existOneSubject.getId();

        //Add secondary category
        //Determine whether the secondary classification is repeated
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//Secondary classification name
            eduSubjectService.save(existTwoSubject);
        }
    }

    //Judging that the first-level classification cannot be added repeatedly
    private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
//        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
//        wrapper.eq("title",name);
//        wrapper.eq("parent_id","0");
//        EduSubject oneSubject = subjectService.getOne(wrapper);
//        return oneSubject;
        return null;
    }

    //Judging that the secondary classification cannot be added repeatedly
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, Long pid) {
//        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
//        wrapper.eq("title",name);
//        wrapper.eq("parent_id",pid);
//        EduSubject twoSubject = subjectService.getOne(wrapper);
//        return twoSubject;
        return null;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
