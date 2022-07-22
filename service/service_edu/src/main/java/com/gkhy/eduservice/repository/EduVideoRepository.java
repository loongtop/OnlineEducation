package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.EduVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EduVideoRepository extends
        JpaSpecificationExecutor<EduVideo>,
        JpaRepository<EduVideo, String> {
}
