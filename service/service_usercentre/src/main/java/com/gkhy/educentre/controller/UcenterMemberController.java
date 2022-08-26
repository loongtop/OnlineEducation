package com.gkhy.educentre.controller;

import com.gkhy.commonutils.jwt.JwtUtils;
import com.gkhy.commonutils.ordervo.UcenterMemberOrder;
import com.gkhy.educentre.entity.form.MemberForm;

import com.gkhy.educentre.service.UcenterMemberService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.educentre.entity.UcenterMember;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * <p>
 * Membership Form Front Controller
 * </p>
 *
 * @author leo
 * @since 2022-07-20
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    private final UcenterMemberService memberService;

    @Autowired
    public UcenterMemberController(UcenterMemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("login")
    public Result loginUser(@RequestBody UcenterMember member) {
        //Member object encapsulates mobile phone number and password
        //Call the service method to log in
        //Return the token value, use jwt to generate
        String token = memberService.login(member);
        return Result.success().data("token", token);
    }


    @PostMapping("register")
    public Result registerUser(@RequestBody MemberForm memberForm) {
        memberService.register(memberForm);
        return Result.success();
    }

    //Get user information based on token
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        //Call the method of the jwt tool class.
        // Get the header information according to the request object and return the user id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //Query the database to obtain user information based on user id
        Optional<UcenterMember> member = memberService.findById(Long.valueOf(memberId));
        if (member.isPresent()) {
            return Result.success().data("userInfo", member);
        }
        return ItemFound.fail();
    }

    @PostMapping("getUserInfoOrder/{id}")
    public Result getUserInfoOrder(@PathVariable Long id) {
        Optional<UcenterMember> member = memberService.findById(id);
        if (member.isPresent()) {
            //Copy the value in the member object to the User center MemberOrder object
            UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
            BeanUtils.copyProperties(member, ucenterMemberOrder);
            return Result.success().data("userInfo", ucenterMemberOrder);
        }
        return ItemFound.fail();
    }
}

