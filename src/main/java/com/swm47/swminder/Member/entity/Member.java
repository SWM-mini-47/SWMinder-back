package com.swm47.swminder.Member.entity;

import com.swm47.swminder.Board.entity.Board;
import com.swm47.swminder.MemberMeetup.entity.MemberMeetup;
import com.swm47.swminder.MemberMentoring.entity.MemberMentoring;
import com.swm47.swminder.Mentoring.entity.Mentoring;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder @ToString(exclude = "boards")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public MemberDTO toDTO() {
        return MemberDTO.builder()
                .memberId(memberId)
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
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    @CollectionTable(joinColumns = @JoinColumn(name = "member_id"))
    private List<String> skills = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Board> boards = new ArrayList<>();

    public void deleteBoard(Board board) {
        boards.remove(board);
        board.setMember(null);
    }

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberMentoring> memberMentorings = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberMeetup> memberMeetups = new ArrayList<>();

    public void deleteMemberMeetup(MemberMeetup memberMeetup) {
        memberMeetups.remove(memberMeetup);
        memberMeetup.setMember(null);
    }

    public void addMemberMentoring(MemberMentoring memberMentoring) {
        memberMentorings.add(memberMentoring);
    }

    public void removeMemberMentoring(MemberMentoring memberMentoring) {
        memberMentorings.remove(memberMentoring);
    }
}
