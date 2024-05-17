package com.example.account.util.service;

import com.example.account.util.domain.Member;
import com.example.account.util.dto.MemberLoginDto;
import com.example.account.util.dto.MemberSignupDto;
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
    public ResponseEntity<CustomApiResponse<?>> login(MemberLoginDto dto) {

        Optional<Member> findMember = memberRepository.findByUserId(dto.getUserId());

        if (findMember.isPresent()) {
            return ResponseEntity.status(404)
                    .body(CustomApiResponse.createFailWithoutData(404, "존재하지 않는 회원입니다."));
        }

        // 옳은 비밀번호인가?
        if (dto.getPassword().equals(findMember.get().getPassword())) {     // Yes -> 로그인 성공
            return ResponseEntity.status(200)
                    .body(CustomApiResponse.createSuccess(200, null, "로그인에 성공했습니다."));
        }else {     // No -> 비밀번호 오류, 로그인 실패
            return ResponseEntity.status(401)
                    .body(CustomApiResponse.createFailWithoutData(401, "비밀번호가 일치하지 않습니다."));
        }
    }

    // 회원가입 로직
    public ResponseEntity<CustomApiResponse<?>> signup(MemberSignupDto dto) {
        Optional<Member> findMember = memberRepository.findByUserId(dto.getUserId());


        // 새 회원에 대한 정보 저장
        Member newMember = Member.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
        memberRepository.save(newMember);

        // 회원가입 성공
        return ResponseEntity.status(201)
                .body(CustomApiResponse.createSuccess(201, null, "회원가입에 성공하였습니다."));

    }


    // 회원탈퇴 로직
    public ResponseEntity<CustomApiResponse<?>> withdraw(String userId) {
        // 현재 로그인한 계정의 userId와 같은 userId를 가진 데이터를 찾는다.
        Optional<Member> findMember = memberRepository.findByUserId(userId);

        // 회원이 없다면 에러
        if (findMember.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(CustomApiResponse.createFailWithoutData(404, "id가 " + userId + "인 회원이 존재하지 않습니다." ));
        }

        // 데이터를 삭제한다.
        memberRepository.delete(findMember.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomApiResponse.createSuccess(HttpStatus.OK.value(), null,"회원이 정상적으로 탈퇴되었습니다."));
    }
}
