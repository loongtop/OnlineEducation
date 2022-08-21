package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.eduservice.entity.VideoEntity;
import com.gkhy.eduservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * Course Video Front Controller
 * </p>
 *
 * @author leo
 * @since 2020-07-18
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public final class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("addVideo")
    public Result addVideo(@RequestBody VideoEntity eduVideo) {
        videoService.save(eduVideo);
        return Result.success();
    }

    // TODO
    //  when deleting a subsection, delete the video inside at the same time
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable Long id) {
        videoService.removeById(id);
        return Result.success();
    }

    //修改小节 TODO
}

