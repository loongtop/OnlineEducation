package com.gkhy.cmsservice.repository;


import com.gkhy.cmsservice.entity.CrmBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CrmBannerRepository extends
        JpaSpecificationExecutor<CrmBanner>,
        JpaRepository<CrmBanner, String> {
}