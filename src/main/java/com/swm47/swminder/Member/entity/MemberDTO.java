package com.swm47.swminder.Member.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long memberId;

    private String username;

    private String loginId;

    private String password;

    private String profileImage;

    private String contact;

    private LocalDateTime birth;

    private String email;

    private String address;

    private String education;

    private List<String> skills;

    public Member toEntity() {
        Member member = Member.builder()
                .username(username)
                .loginId(loginId)
                .password(password)
                .profileImage(profileImage)
                .contact(contact)
                .birth(birth)
                .email(email)
                .address(address)
                .education(education)
                .build();

        skills.forEach(skill -> member.getSkills().add(skill));
        return member;
    }
}
