package com.gkhy.ossservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Component
public class UploadConfig {
    //显示声明CommonsMultipartResolver为mutipartResolver
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setResolveLazily(true);
        //设置了文件放入临时文件夹的最小大小限制
        resolver.setMaxInMemorySize(40960);
        //设置单个上传数据总大小25M
        resolver.setMaxUploadSizePerFile(25*1024*1024);
        //设置总上传数据总大小50M
        resolver.setMaxUploadSize(5 * 1024 * 1024);
        return resolver;
    }
}
