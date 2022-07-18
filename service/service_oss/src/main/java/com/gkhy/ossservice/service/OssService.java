package com.gkhy.ossservice.service;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.net.URL;

public interface OssService {

    Mono<URL> uploadFile(FilePart file);
}
