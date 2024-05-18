package com.example.account.accounts.service;

import com.example.account.accounts.dto.MemberLoginDto;
import com.example.account.accounts.dto.MemberSignupDto;
import com.example.account.util.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    public ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto dto);
    public ResponseEntity<CustomApiResponse<?>> signup(MemberSignupDto dto);
    public ResponseEntity<CustomApiResponse<?>> withdraw(String userId);
}
