package com.gkhy.educentre.service;

import com.gkhy.educentre.entity.UcenterMember;
import com.gkhy.educentre.entity.form.MemberForm;

import java.util.Optional;


public interface UcenterMemberService {

    String login(UcenterMember member);

    UcenterMember getOpenIdMember(String openid);

    Optional<UcenterMember> findById(Long id);

    void register(MemberForm memberForm);
}
