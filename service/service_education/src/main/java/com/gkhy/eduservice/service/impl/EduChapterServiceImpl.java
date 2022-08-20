package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.EduChapter;
import com.gkhy.eduservice.entity.EduVideo;
import com.gkhy.eduservice.entity.chapter.ChapterVo;
import com.gkhy.eduservice.entity.chapter.VideoVo;
import com.gkhy.eduservice.repository.EduChapterRepository;
import com.gkhy.eduservice.service.EduChapterService;
import com.gkhy.eduservice.service.EduVideoService;
import com.gkhy.servicebase.exceptionhandler.EducationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * EduChapter Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-18
 */

@Service
public class EduChapterServiceImpl implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterRepository eduChapterRepository;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //Create Specification object
        Specification<EduChapter> specEduChapter =
                (root, query, cb) -> cb.equal(root.get("courseId"), courseId);
        //1 Query all chapters in the course according to the course id
        List<EduChapter> eduChapterList = eduChapterRepository.findAll(specEduChapter);

        Specification<EduVideo> specEduVideo =
                (root, query, cb) -> cb.equal(root.get("courseId"), courseId);
        //Query all the subsections in the course according to the course id
        List<EduVideo> eduVideoList = eduVideoService.findAll(specEduVideo);

        //Create a list collection for final encapsulation of data
        List<ChapterVo> finalList = new ArrayList<>();

        for(EduChapter eduChapter : eduChapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);

            List<VideoVo> videoList = new ArrayList<>();
            for(EduVideo eduVideo : eduVideoList) {
                if(eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }

    @Override
    public void deleteChapter(String chapterId) {

        Long count = eduVideoService.count(chapterId);
        if(count > 0) {  //Section exists, do not delete
            throw new EducationException(20001,"Can not delete!");
        }
        else { //Section does not exist, delete
            eduVideoService.deleteById(chapterId);
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        eduChapterRepository.deleteById(courseId);
    }

    @Override
    public void save(EduChapter eduChapter) {
        eduChapterRepository.save(eduChapter);
    }

    @Override
    public EduChapter getById(String chapterId) {
        return eduChapterRepository.getReferenceById(chapterId);
    }

    @Override
    public void update(EduChapter eduChapter) {
        eduChapterRepository.save(eduChapter);
    }

    @Override
    public Optional<EduChapter> findById(String id) {
        return eduChapterRepository.findById(id);
    }
}
