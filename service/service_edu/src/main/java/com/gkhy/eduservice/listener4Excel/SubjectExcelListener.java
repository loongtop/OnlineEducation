package com.gkhy.eduservice.listener4Excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.gkhy.eduservice.entity.EduSubject;
import com.gkhy.eduservice.entity.excel.SubjectData;
import com.gkhy.eduservice.service.EduSubjectService;
import com.gkhy.servicebase.exceptionhandler.EducationException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        EduSubject eduSubject = existOneSubject(this.eduSubjectService, subjectData.getOneSubjectName());
        if (eduSubject.equals(Optional.empty())){
            //if There is no same first-level classification, add it
            eduSubject = new EduSubject() ;
            eduSubject.setParentId(0L);
            eduSubject.setTitle(subjectData.getOneSubjectName());//first class name
            eduSubjectService.save(eduSubject);
        }

        //Get the first-level classification id value
        Long pid = eduSubject.getId();

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
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {

        Specification<EduSubject> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();

            list.add(cb.equal(root.get("title"), name));
            list.add(cb.equal(root.get("parentId"), 0));

            Predicate[] arr = new Predicate[list.size()];
            return cb.and(list.toArray(arr));
        };

        Optional<EduSubject> eduSubject = eduSubjectService.findOne(specification);
        return eduSubject.orElse(null);
    }

    //Judging that the secondary classification cannot be added repeatedly
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, Long pid) {
        Specification<EduSubject> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();

            list.add(cb.equal(root.get("title"), name));
            list.add(cb.equal(root.get("parentId"), pid));

            Predicate[] arr = new Predicate[list.size()];
            return cb.and(list.toArray(arr));
        };

        Optional<EduSubject> eduSubject = eduSubjectService.findOne(specification);
        return eduSubject.orElse(null);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //TODO
    }
}
