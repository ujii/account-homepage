package com.example.account.util.service;

import com.example.account.util.domain.Member;
import com.example.account.util.dto.UserLoginDto;
import com.example.account.util.dto.UserSignupDto;
import com.example.account.util.repository.MemberRepository;
import com.example.account.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 로그인 로직
    public ResponseEntity<CustomApiResponse<?>> login(UserLoginDto dto) {

        Optional<Member> findMember = memberRepository.findByUserId(dto.getUserId());

        // 옳은 비밀번호인가?
        if (dto.getPassword().equals(memberRepository.findByUserId(dto.getUserId()).get().getPassword())) {     // Yes -> 로그인 성공
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "로그인에 성공했습니다."));
        }else {     // No -> 비밀번호 오류, 로그인 실패
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."));
        }
    }

    // 회원가입 로직
    public ResponseEntity<CustomApiResponse<?>> signup(UserSignupDto dto) {
        Optional<Member> findMember = memberRepository.findByUserId(dto.getUserId());

        // 존재하는 ID라면 가입 불가
        if (findMember.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CustomApiResponse.createFailWithoutData(HttpStatus.BAD_REQUEST.value(), "존재하는 ID입니다."));
        }

        // 존재하지 않는 아이디라면
        // 새 회원에 대한 정보 저장
        Member newMember = Member.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
        memberRepository.save(newMember);

        // 회원가입 성공
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "회원가입에 성공했습니다."));

    }


    // 회원탈퇴 로직
}
