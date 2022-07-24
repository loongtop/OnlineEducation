package com.gkhy.cmsservice.service.impl;

import com.gkhy.cmsservice.entity.CrmBanner;
import com.gkhy.cmsservice.repository.CrmBannerRepository;
import com.gkhy.cmsservice.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmBannerServiceImpl implements CrmBannerService {
    @Autowired
    private CrmBannerRepository crmBannerRepository;

    @Override
    public List<CrmBanner> findAll() {
        return crmBannerRepository.findAll();
    }

    @Override
    public void save(CrmBanner crmBanner) {
        crmBannerRepository.save(crmBanner);
    }

    @Override
    public Page<CrmBanner> findAll(long page, long limit) {
        Pageable pageable = PageRequest.of((int) (page-1), (int) limit);
        return crmBannerRepository.findAll(pageable);
    }

    @Override
    public CrmBanner getById(String id) {
        return crmBannerRepository.getReferenceById(id);
    }

    @Override
    public void update(CrmBanner banner) {
        crmBannerRepository.save(banner);
    }

    @Override
    public void removeById(String id) {
        crmBannerRepository.deleteById(id);
    }
}
