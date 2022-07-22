package com.gkhy.eduservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.eduservice.entity.EduVideo;
import com.gkhy.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;

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
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return Result.success();
    }

    // TODO
    //  when deleting a subsection, delete the video inside at the same time
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable String id) {
        videoService.removeById(id);
        return Result.success();
    }

    //修改小节 TODO

}

