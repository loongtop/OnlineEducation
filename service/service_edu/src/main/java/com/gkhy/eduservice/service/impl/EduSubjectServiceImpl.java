package com.gkhy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.gkhy.eduservice.entity.EduSubject;
import com.gkhy.eduservice.entity.EduTeacher;
import com.gkhy.eduservice.entity.excel.SubjectData;
import com.gkhy.eduservice.entity.subject.OneSubject;
import com.gkhy.eduservice.entity.subject.TwoSubject;
import com.gkhy.eduservice.listener4Excel.SubjectExcelListener;
import com.gkhy.eduservice.repository.EduSubjectRepository;
import com.gkhy.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * EduSubject Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-18
 */

@Service
public class EduSubjectServiceImpl implements EduSubjectService {

    @Autowired
    private EduSubjectRepository eduSubjectRepository;

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
        //Create Specification object
        Specification<EduSubject> specification =
                (root, query, cb) -> cb.equal(root.get("parentId"), "0");

        //Create a list collection to store the final encapsulated data
        List<EduSubject> oneSubjectList = eduSubjectRepository.findAll(specification);

        specification = (root, query, cb) -> cb.notEqual(root.get("parentId"), "0");

        List<EduSubject> twoSubjectList = eduSubjectRepository.findAll(specification);

        List<OneSubject> finalSubjectList = new ArrayList<>();

        for(EduSubject eduSubject1: oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject1,oneSubject);
            finalSubjectList.add(oneSubject);

            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for(EduSubject eduSubject2: twoSubjectList) {
                if(eduSubject2.getParentId().equals(eduSubject1.getId())) {

                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
    }

    @Override
    public void save(EduSubject existOneSubject) {
        eduSubjectRepository.save(existOneSubject);
    }

    public Optional<EduSubject> findOne(Specification<EduSubject> specification) {
        return eduSubjectRepository.findOne(specification);
    }

}
