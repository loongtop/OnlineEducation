package com.gkhy.ossservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.ossservice.config.UploadConfig;
import com.gkhy.ossservice.service.OssService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.net.URL;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @SneakyThrows
    @PostMapping(value = "/upload")
    public Result uploadOssFile(MultipartFile file) {
        //Returns the path uploaded to oss
        URL url = ossService.uploadFile(file);
        return Result.success().data("url", url);
    }
}
