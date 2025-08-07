package com.metacoding.spirngv1.Controller;

import com.metacoding.spirngv1.DTO.JoinRequestDTO;
import com.metacoding.spirngv1.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private  final UserService userService;


    @GetMapping("/join-form")
    public String joinForm(){
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm(){
        return "user/login-form";
    }

    @GetMapping("/join")
    public String join(JoinRequestDTO reqDTO){
        userService.회원가입(reqDTO);
        return "redirect:/login-form";
    }
}
