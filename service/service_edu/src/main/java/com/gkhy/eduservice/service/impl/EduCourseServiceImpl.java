package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.EduCourse;
import com.gkhy.eduservice.entity.EduCourseDescription;
import com.gkhy.eduservice.entity.vo.CourseInfoVo;
import com.gkhy.eduservice.entity.vo.CoursePublishVo;
import com.gkhy.eduservice.repository.EduCourseRepository;
import com.gkhy.eduservice.service.EduChapterService;
import com.gkhy.eduservice.service.EduCourseDescriptionService;
import com.gkhy.eduservice.service.EduCourseService;
import com.gkhy.eduservice.service.EduVideoService;
import com.gkhy.servicebase.exceptionhandler.EducationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * EduCourse Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-17
 */

@Service
public class EduCourseServiceImpl implements EduCourseService {

    @Autowired
    private EduCourseRepository eduCourseRepository;

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService chapterService;

    @Override
    public List<EduCourse> findAll() {
        return eduCourseRepository.findAll();
    }

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //CourseInfoVo object converts eduCourse object
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        eduCourseRepository.save(eduCourse);
        int insert = 0;
        if(insert == 0) {
            throw new EducationException(20001,"Failed to add course information");
        }

        String cid = eduCourse.getId();

        //2 Add a course introduction to the course introduction table
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //Set the description id to be the course id
        courseDescription.setId(cid);
        eduCourseDescriptionService.save(courseDescription);

        return cid;
    }

    private void save(EduCourse eduCourse) {
        eduCourseRepository.save(eduCourse);
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        return null;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        eduCourseRepository.save(eduCourse);
    }

    @Override
    public void updateById(EduCourse eduCourse) {
        eduCourseRepository.save(eduCourse);
    }

    @Override
    public void removeCourse(String courseId) {
        eduVideoService.removeVideoByCourseId(courseId);

        //2 Delete chapters based on course id
        chapterService.removeChapterByCourseId(courseId);

        //3 Remove description based on course id
        eduCourseDescriptionService.removeById(courseId);    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        return null;
    }
}
