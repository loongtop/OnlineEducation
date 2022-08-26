package com.gkhy.educentre.repository;

import com.gkhy.educentre.entity.UcenterMember;
import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface UcenterMemberRepository extends IRepositoryBase<UcenterMember, Long> {
}