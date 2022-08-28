package com.gkhy.serviceoauth2.controller;

import com.gkhy.commonutils.jwt.JwtUtils;
import com.gkhy.commonutils.ordervo.UcenterMemberOrder;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import com.gkhy.serviceoauth2.error.Oauth2Error;
import com.gkhy.serviceoauth2.exception.BadRequestException;
import com.gkhy.serviceoauth2.entity.User;
import com.gkhy.serviceoauth2.entity.request.LoginRequest;
import com.gkhy.serviceoauth2.entity.request.SignUpRequest;
import com.gkhy.serviceoauth2.entity.response.ApiResponse;
import com.gkhy.serviceoauth2.entity.response.AuthResponse;
import com.gkhy.serviceoauth2.service.UserDetailsService;
import com.gkhy.serviceoauth2.token.TokenProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
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
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(),
                                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userDetailsService.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException(Oauth2Error.Email_Already_Occupied);
        }

        // Creating user's account
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        User user = new User();
//                .builder()
//                .username(signUpRequest.getName())
//                .email(signUpRequest.getEmail())
//                .password(password)
//                .provider(AuthProvider.LOCAL).build();

        userDetailsService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

    //Get user information based on token
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        //Call the method of the jwt tool class.
        // Get the header information according to the request object and return the user id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //Query the database to obtain user information based on user id
        Optional<User> member = userDetailsService.findById(Long.valueOf(memberId));
        if (member.isPresent()) {
            return Result.success().data("userInfo", member);
        }
        return ItemFound.fail();
    }

    @PostMapping("getUserInfoOrder/{id}")
    public Result getUserInfoOrder(@PathVariable Long id) {
        Optional<User> member = userDetailsService.findById(id);
        if (member.isPresent()) {
            //Copy the value in the member object to the User center MemberOrder object
            UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
            BeanUtils.copyProperties(member, ucenterMemberOrder);
            return Result.success().data("userInfo", ucenterMemberOrder);
        }
        return ItemFound.fail();
    }

}
