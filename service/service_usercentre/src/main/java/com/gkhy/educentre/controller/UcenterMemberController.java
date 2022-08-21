package com.gkhy.educentre.controller;

import com.gkhy.commonutils.JwtUtils;
import com.gkhy.commonutils.ordervo.UcenterMemberOrder;
import com.gkhy.servicebase.result.Result;
import com.gkhy.educentre.entity.UcenterMember;
import com.gkhy.educentre.entity.vo.RegisterVo;
import com.gkhy.educentre.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("login")
    public Result loginUser(@RequestBody UcenterMember member) {
        //Member object encapsulates mobile phone number and password
        //Call the service method to log in
        //Return the token value, use jwt to generate
        String token = memberService.login(member);
        return Result.success().data("token",token);
    }


    @PostMapping("register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return Result.success();
    }

    //Get user information based on token
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        //Call the method of the jwt tool class.
        // Get the header information according to the request object and return the user id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //Query the database to obtain user information based on user id
        UcenterMember member = memberService.getById(memberId);
        return Result.success().data("userInfo",member);
    }

    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        //Copy the value in the member object to the UcenterMemberOrder object
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }
}

