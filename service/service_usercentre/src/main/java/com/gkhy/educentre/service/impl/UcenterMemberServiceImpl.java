package com.gkhy.educentre.service.impl;

import com.gkhy.commonutils.encryption.MD5;
import com.gkhy.commonutils.jwt.JwtUtils;
import com.gkhy.educentre.entity.UcenterMember;
import com.gkhy.educentre.entity.vo.RegisterVo;
import com.gkhy.educentre.repository.UcenterMemberRepository;
import com.gkhy.educentre.service.UcenterMemberService;
import com.gkhy.servicebase.exception.AcademyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * <p>
 * Membership table Service implementation clas
 * </p>
 *
 * @author leo
 * @since 2022-07-20
 */
@Service
public class UcenterMemberServiceImpl implements UcenterMemberService {

    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UcenterMemberRepository ucenterMemberRepository;

    @Override
    public String login(UcenterMember member) {
        //Get login phone number and password
        String mobile = member.getMobile();
        String password = member.getPassword();

        //Mobile phone number and password are not empty judgment
        if(!StringUtils.hasLength(mobile) || !StringUtils.hasLength(password)) {
            throw new AcademyException(20001,"Fail to login");
        }

        Specification<UcenterMember> spe = (root, query, cb) -> cb.like(root.get("mobile"), mobile);
        Optional<UcenterMember> ucenterMember = ucenterMemberRepository.findOne(spe);

        UcenterMember mobileMember = ucenterMember.orElseThrow(() -> new AcademyException(20001,"Fail to login"));

        //Get the login phone number and password
        // Judge the password
        //Because the password stored in the database is definitely encrypted
        //Encrypt the entered password and compare it with the database password
        //Encryption method MD5
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new AcademyException(20001,"Fail to login");
        }

        //Determine if the user is disabled
        if(mobileMember.getIsDisabled()) {
            throw new AcademyException(20001,"Fail to login");
        }

        //login successful
        //Generate token string, use jwt tool class
        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
    }

    @Override
    public void register(RegisterVo registerVo) {

        //Get registered data
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        //non-empty checking
        if(!StringUtils.hasLength(mobile) || !StringUtils.hasLength(password)
                || !StringUtils.hasLength(code) || !StringUtils.hasLength(nickname)) {
            throw new AcademyException(20001,"Fail to register");
        }

        //Judge the verification code
        //Get redis verification code
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)) {
            throw new AcademyException(20001,"Fail to register");
        }

        //Determine whether the mobile phone number is repeated,
        // if the same mobile phone number exists in the table, do not add it
        Specification<UcenterMember> spe = (root, query, cb) -> cb.like(root.get("mobile"), mobile);
        if(ucenterMemberRepository.findAll(spe).size() > 0) {
            throw new AcademyException(20001,"Fail to register");
        }

        //data is added to the database
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        ucenterMemberRepository.save(member);
    }

    @Override
    public UcenterMember getById(String memberId) {
        return ucenterMemberRepository.getReferenceById(memberId);
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        Specification<UcenterMember> spe = (root, query, cb) -> cb.like(root.get("openid"), openid);
        Optional<UcenterMember> member = ucenterMemberRepository.findOne(spe);
        return member.orElseThrow(() -> new AcademyException(20001, "Can not find user by openId!"));
    }
}
