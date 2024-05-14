package com.example.account.util.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignupDto {
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    private String email;
    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;
    @NotBlank(message = "ID를 입력해주세요.")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
