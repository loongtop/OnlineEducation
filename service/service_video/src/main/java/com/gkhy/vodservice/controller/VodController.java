package com.gkhy.vodservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.servicebase.exceptionhandler.EducationException;
import com.gkhy.vodservice.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadAlyiVideo")
    public Result uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return Result.success().data("videoId",videoId);
    }

    @DeleteMapping("removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable String id) {
        try {
//            //初始化对象
//            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
//            //创建删除视频request对象
//            DeleteVideoRequest request = new DeleteVideoRequest();
//            //向request设置视频id
//            request.setVideoIds(id);
//            //调用初始化对象的方法实现删除
//            client.getAcsResponse(request);
            return Result.success();
        }catch(Exception e) {
            e.printStackTrace();
            throw new EducationException(20001,"删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return Result.success();
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id) {
        try {
//            //创建初始化对象
//            DefaultAcsClient client =
//                    InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
//            //创建获取凭证request和response对象
//            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
//            //向request设置视频id
//            request.setVideoId(id);
//            //调用方法得到凭证
//            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
//            String playAuth = response.getPlayAuth();
            return Result.success().data("playAuth","playAuth");
        }catch(Exception e) {
            throw new EducationException(20001,"获取凭证失败");
        }
    }
}
