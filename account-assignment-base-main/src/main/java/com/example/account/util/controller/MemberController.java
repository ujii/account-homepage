package com.example.account.util.controller;

import com.example.account.util.dto.MemberLoginDto;
import com.example.account.util.dto.MemberSignupDto;
import com.example.account.util.response.CustomApiResponse;
import com.example.account.util.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<?>> login(@RequestBody MemberLoginDto dto) {
        return memberService.login(dto);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CustomApiResponse<?>> signup(@Valid @RequestBody MemberSignupDto dto) {
        return memberService.signup(dto);
    }

    @DeleteMapping("/withdraw/{userId}")
    public ResponseEntity<CustomApiResponse<?>> withdraw(@PathVariable("userId") String userId) {
        return memberService.withdraw(userId);
    }
}
