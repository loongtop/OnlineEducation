package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.ChapterEntity;
import org.springframework.stereotype.Repository;
import com.gkhy.servicebase.service.repository.IJpaRepository;
import com.gkhy.servicebase.service.repository.IRepositoryBase;

@Repository
public interface ChapterRepository extends
        IRepositoryBase<ChapterEntity, Long>,
        IJpaRepository<ChapterEntity, Long> {
}
