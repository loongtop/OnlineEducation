package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.eduservice.entity.EduChapter;
import com.gkhy.eduservice.entity.chapter.ChapterVo;
import com.gkhy.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Course Front Controller
 * </p>
 *
 * @author leo
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    //List of course syllabus, query according to course id
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return Result.success().data("allChapterVideo", list);
    }

    @PostMapping("addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return Result.success();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public Result getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return Result.success().data("chapter", eduChapter);
    }

    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapterIn) {
        String id = eduChapterIn.getId();
        Optional<EduChapter> eduChapter = eduChapterService.findById(id);
        if (eduChapter.isPresent()) {
            eduChapterService.update(eduChapterIn);
            return Result.success().data("Chapter", eduChapter);
        }
        String message = "Did not find Chapter with ID %s";
        return Result.fail().data("Chapter", String.format(message, id));
    }

    @DeleteMapping("{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId) {
        eduChapterService.deleteChapter(chapterId);
        boolean flag = true;
        if (flag) {
            return Result.success();
        } else {
            return Result.fail();
        }

    }
}

