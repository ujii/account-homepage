package com.example.account.util.controller;

import com.example.account.util.dto.UserLoginDto;
import com.example.account.util.dto.UserSignupDto;
import com.example.account.util.response.CustomApiResponse;
import com.example.account.util.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> login(@RequestBody UserLoginDto dto) {
        return memberService.login(dto);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CustomApiResponse<?>> signup(@Valid @RequestBody UserSignupDto dto) {
        return memberService.signup(dto);
    }

//    @PostMapping("/withdraw")
//    public ResponseEntity<CustomApiResponse<?>> withdraw (@RequestBody )
}
