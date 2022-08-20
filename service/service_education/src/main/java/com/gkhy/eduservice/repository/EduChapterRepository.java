package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.EduChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface EduChapterRepository extends
        JpaSpecificationExecutor<EduChapter>,
        JpaRepository<EduChapter, String> {
}