package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.CourseDescriptionEntity;
import com.gkhy.eduservice.entity.CourseEntity;
import com.gkhy.eduservice.entity.vo.CourseInfoVo;
import com.gkhy.eduservice.entity.vo.CoursePublishVo;
import com.gkhy.eduservice.repository.CourseRepository;
import com.gkhy.eduservice.service.ChapterService;
import com.gkhy.eduservice.service.CourseDescriptionService;
import com.gkhy.eduservice.service.CourseService;
import com.gkhy.eduservice.service.VideoService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * EduCourse Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-17
 */

@Service
public final class CourseServiceImpl extends
        ServiceImpl<CourseEntity, Long, CourseRepository>
        implements CourseService {

    private final CourseDescriptionService courseDescriptionService;
    private final VideoService videoService;
    private final ChapterService chapterService;

    @Autowired
    public CourseServiceImpl(CourseRepository iRepository, CourseDescriptionService courseDescriptionService, VideoService videoService, ChapterService chapterService) {
        super(iRepository);
        this.courseDescriptionService = courseDescriptionService;
        this.videoService = videoService;
        this.chapterService = chapterService;
    }

    @Override
    public Long saveCourseInfo(CourseInfoVo courseInfoVo) {
        //CourseInfoVo object converts eduCourse object
        CourseEntity eduCourse = new CourseEntity();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        this.save(eduCourse);

        Long cid = eduCourse.getId();

        //2 Add a course introduction to the course introduction table
        //edu_course_description
        CourseDescriptionEntity courseDescription = new CourseDescriptionEntity();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //Set the description id to be the course id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        CourseEntity eduCourse = new CourseEntity();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        this.save(eduCourse);
    }

    @Override
    public void removeById(Long courseId) {
        videoService.removeById(courseId);

        //2 Delete chapters based on course id
        chapterService.removeById(courseId);

        //3 Remove description based on course id
        courseDescriptionService.removeById(courseId);    }

    @Override
    public CoursePublishVo publishCourseInfo(Long id) {
        return null;
    }

    @Override
    public CourseInfoVo getCourseInfo(Long courseId) {
        return null;
    }

    @Override
    public void updateById(CourseEntity eduCourse) {

    }

    @Override
    public void removeCourse(Long courseId) {

    }
}
