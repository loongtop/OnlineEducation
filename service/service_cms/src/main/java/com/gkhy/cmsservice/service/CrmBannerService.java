package com.gkhy.cmsservice.service;

import com.gkhy.cmsservice.entity.CrmBanner;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CrmBannerService {
    List<CrmBanner> findAll();

    void save(CrmBanner crmBanner);

    Page<CrmBanner> findAll(long page, long limit);

    CrmBanner getById(String id);

    void removeById(String id);

    void update(CrmBanner banner);
}
