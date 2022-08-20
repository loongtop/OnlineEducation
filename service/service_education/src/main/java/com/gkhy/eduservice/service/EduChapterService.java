package com.gkhy.eduservice.service;


import com.gkhy.eduservice.entity.EduChapter;
import com.gkhy.eduservice.entity.chapter.ChapterVo;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * course service
 * </p>
 *
 * @author leo
 * @since 2022-07-20
 */
public interface EduChapterService {

    //List of course syllabus, query according to course id
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    void deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);

    void save(EduChapter eduChapter);

    EduChapter getById(String chapterId);

    Optional<EduChapter> findById(String id);

    void update(EduChapter eduChapterIn);
}
