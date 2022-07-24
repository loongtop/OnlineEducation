package com.gkhy.educentre.repository;

import com.gkhy.educentre.entity.UcenterMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UcenterMemberRepository extends
        JpaSpecificationExecutor<UcenterMember>,
        JpaRepository<UcenterMember, String> {
}