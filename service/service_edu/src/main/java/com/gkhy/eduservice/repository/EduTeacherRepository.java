package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.EduTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EduTeacherRepository extends JpaRepository<EduTeacher, Long> {
}
