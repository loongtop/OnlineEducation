package com.gkhy.serviceoauth2.controller;

//import com.gkhy.serviceoauth2.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//    private final UserRepository userRepository;
//    @Autowired
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @GetMapping("/user/me")
//    @PreAuthorize("hasRole('USER')")
//    public User getCurrentUser(@CurrentUser UserFactory userPrincipal) {
//        return userRepository.findById(userPrincipal.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
//    }
}
