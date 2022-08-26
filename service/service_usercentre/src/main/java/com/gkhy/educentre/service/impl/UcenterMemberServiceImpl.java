package com.gkhy.educentre.service.impl;

import com.gkhy.commonutils.encryption.MD5;
import com.gkhy.commonutils.jwt.JwtUtils;
import com.gkhy.educentre.entity.UcenterMember;
import com.gkhy.educentre.entity.form.MemberForm;
import com.gkhy.educentre.error.UserCentreError;
import com.gkhy.educentre.repository.UcenterMemberRepository;
import com.gkhy.educentre.service.UcenterMemberService;
import com.gkhy.servicebase.exception.AcademyException;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
public class UcenterMemberServiceImpl
        extends ServiceImpl<UcenterMember, Long, UcenterMemberRepository>
        implements UcenterMemberService {

    private final RedisService redisService;
    @Autowired
    public UcenterMemberServiceImpl(UcenterMemberRepository iRepository, RedisService redisService) {
        super(iRepository);
        this.redisService = redisService;
    }

    public String login(UcenterMember member) {
        //Get login phone number and password
        String mobile = member.getMobile();
        String password = member.getPassword();

        //Mobile phone number and password are not empty judgment
        if (!StringUtils.hasLength(mobile) || !StringUtils.hasLength(password)) {
            throw new AcademyException(UserCentreError.LOGIN_ERROR.getCode(), UserCentreError.LOGIN_ERROR.getMessage());
        }

        Specification<UcenterMember> spe = (root, query, cb) -> cb.like(root.get("mobile"), mobile);
        Optional<UcenterMember> ucenterMember = this.findOne(spe);

        UcenterMember mobileMember =
                ucenterMember.orElseThrow(() ->
                        new AcademyException(UserCentreError.LOGIN_ERROR.getCode(),
                                UserCentreError.LOGIN_ERROR.getMessage()));
        //Get the login phone number and password
        // Judge the password
        //Because the password stored in the database is definitely encrypted
        //Encrypt the entered password and compare it with the database password
        //Encryption method MD5
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new AcademyException(UserCentreError.LOGIN_ERROR.getCode(),
                    UserCentreError.LOGIN_ERROR.getMessage());
        }

        //Determine if the user is disabled
        if (mobileMember.getIsDisabled()) {
            throw new AcademyException(UserCentreError.LOGIN_ERROR.getCode(),
                    UserCentreError.LOGIN_ERROR.getMessage());
        }

        //login successful
        //Generate token string, use jwt tool class
        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
    }

    public void register(MemberForm memberForm) {

        //Get registered data
        String code = memberForm.getCode();
        String mobile = memberForm.getMobile();
        String nickname = memberForm.getNickname();
        String password = memberForm.getPassword();

        //non-empty checking
        if (!StringUtils.hasLength(mobile)
                || !StringUtils.hasLength(password)
                || !StringUtils.hasLength(code)
                || !StringUtils.hasLength(nickname)) {
            throw new AcademyException(20001, "Fail to register");
        }

        //Judge the verification code
        //Get redis verification code
        String redisCode = (String) redisService.get(mobile);
        if (!code.equals(redisCode)) {
            throw new AcademyException(20001, "Fail to register");
        }

        //Determine whether the mobile phone number is repeated,
        // if the same mobile phone number exists in the table, do not add it
        Specification<UcenterMember> spe = (root, query, cb) -> cb.like(root.get("mobile"), mobile);
        if (this.findAll(spe).size() > 0) {
            throw new AcademyException(20001, "Fail to register");
        }

        String avatar = "http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132";
        //data is added to the database
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar(avatar);
        this.save(member);
    }

    public UcenterMember getOpenIdMember(String openid) {
        Specification<UcenterMember> spe = (root, query, cb) -> cb.like(root.get("openid"), openid);
        Optional<UcenterMember> member = this.findOne(spe);
        return member.orElseThrow(() -> new AcademyException(20001, "Can not find user by openId!"));
    }
}
