package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.EduVideo;
import com.gkhy.eduservice.repository.EduVideoRepository;
import com.gkhy.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * EduTeacher Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-18
 */
@Service
public class EduVideoServiceImpl implements EduVideoService {

    @Autowired
    private EduVideoRepository eduVideoRepository;

    // TODO
    //  Delete a section, delete the corresponding video file
    @Override
    public void removeVideoByCourseId(String courseId) {
        eduVideoRepository.deleteById(courseId);
    }

    @Override
    public Long count(String chapterId) {
        //Create Specification object
        Specification< EduVideo > specification =
                (root, query, cb) -> cb.equal(root.get("chapterId"), chapterId);
        return eduVideoRepository.count(specification);
    }

    @Override
    public void deleteById(String chapterId) {
        eduVideoRepository.deleteById(chapterId);
    }

    @Override
    public List<EduVideo> findAll(Specification<EduVideo> specEduVideo) {
        return eduVideoRepository.findAll(specEduVideo);
    }

    @Override
    public void save(EduVideo eduVideo) {
        eduVideoRepository.save(eduVideo);
    }

    @Override
    public void removeById(String id) {
        eduVideoRepository.deleteById(id);
    }
}
