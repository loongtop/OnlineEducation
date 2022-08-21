package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.eduservice.entity.ChapterEntity;
import com.gkhy.eduservice.entity.chapter.ChapterVo;
import com.gkhy.eduservice.service.ChapterService;
import com.gkhy.servicebase.utils.ItemFound;
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
public final class ChapterController {

    private final ChapterService eduChapterService;
    @Autowired
    public ChapterController(ChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }

    //List of course syllabus, query according to course id
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable Long courseId) {
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return Result.success().data("allChapterVideo", list);
    }

    @PostMapping("addChapter")
    public Result addChapter(@RequestBody ChapterEntity eduChapter) {
        eduChapterService.save(eduChapter);
        return Result.success();
    }

    @GetMapping("get/{chapterId}")
    public Result getChapterInfo(@PathVariable Long chapterId) {
        Optional<ChapterEntity> chapterEntity = eduChapterService.findById(chapterId);
        if (chapterEntity.isEmpty()) {
            return ItemFound.fail();
        }
        return Result.success().data("teacher", chapterEntity.get());

    }

    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody ChapterEntity eduChapterIn) {
        Long id = eduChapterIn.getId();
        Optional<ChapterEntity> eduChapter = eduChapterService.findById(id);
        if (eduChapter.isPresent()) {
            eduChapterService.save(eduChapterIn);
            return Result.success().data("Chapter", eduChapter);
        }
        String message = "Did not find Chapter with ID %s";
        return Result.fail().data("Chapter", String.format(message, id));
    }

    @DeleteMapping("{chapterId}")
    public Result deleteChapter(@PathVariable Long chapterId) {
        eduChapterService.deleteById(chapterId);
        return Result.success();
    }
}

