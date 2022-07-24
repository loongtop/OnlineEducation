package com.gkhy.educentre.service;

import com.gkhy.educentre.entity.UcenterMember;
import com.gkhy.educentre.entity.vo.RegisterVo;

/**
 * <p>
 * Membership Form Services
 * </p>
 *
 * @author leo
 * @since 2022-07-20
 */
public interface UcenterMemberService  {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getById(String memberId);

    UcenterMember getOpenIdMember(String openid);
}
