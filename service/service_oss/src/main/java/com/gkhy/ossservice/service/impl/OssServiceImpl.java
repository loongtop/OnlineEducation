package com.gkhy.ossservice.service.impl;

import com.gkhy.ossservice.service.OssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        return "";
    }

}
