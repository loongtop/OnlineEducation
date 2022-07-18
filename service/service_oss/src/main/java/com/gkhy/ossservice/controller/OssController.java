package com.gkhy.ossservice.controller;

import com.gkhy.commonutils.result.Result;
import com.gkhy.ossservice.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URL;


@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

//    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public Result uploadOssFile(@RequestPart("file") Flux<FilePart> file) {
    public Result uploadOssFile(@RequestPart("file") FilePart file) {
        //Returns the path uploaded to oss
        System.out.println("--------uploadOssFile------");
        Mono<URL> urlMono = ossService.uploadFile(file);
        return Result.success().data("url", urlMono.toString());
    }
}
