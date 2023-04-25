package com.swm47.swminder.Member.service;

import com.swm47.swminder.Member.entity.Member;
import com.swm47.swminder.Member.entity.MemberDTO;
import com.swm47.swminder.Member.repository.MemberRepository;
import com.swm47.swminder.MemberMentoring.entity.MemberMentoring;
import com.swm47.swminder.MemberMentoring.repository.MemberMentoringRepository;
import com.swm47.swminder.Mentoring.entity.Mentoring;
import com.swm47.swminder.Mentoring.repository.MentoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MentoringRepository mentoringRepository;
    private final MemberMentoringRepository memberMentoringRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long saveMember(MemberDTO memberDTO) {
        memberDTO.setPassword(bCryptPasswordEncoder.encode(memberDTO.getPassword()));
        Member savedMember = memberRepository.save(memberDTO.toEntity());
        return savedMember.getMemberId();
    }

    public MemberDTO findMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();
        Optional<Member> result = memberRepository.findByLoginId(loginId);
        Member member = result.get();
        return member.toDTO();
    }

    public Long joinMentoring(Long memberId, Long mentoringId) {
        Mentoring mentoring = mentoringRepository.findById(mentoringId).orElseThrow(() -> new RuntimeException("invalid mentoringId"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("invalid memberId"));

        MemberMentoring memberMentoring = MemberMentoring.createMemberMentoring(member, mentoring);
        member.addMemberMentoring(memberMentoring);
        mentoring.addMemberMentoring(memberMentoring);
        return member.getMemberId();
    }

    public Long unJoinMentoring(Long memberId, Long mentoringId) {
        Mentoring mentoring = mentoringRepository.findById(mentoringId).orElseThrow(() -> new RuntimeException("invalid mentoringId"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("invalid memberId"));
        MemberMentoring memberMentoring = memberMentoringRepository.findMemberMentoringByMemberAndMentoring(memberId, mentoringId).orElseThrow(() -> new RuntimeException("memberMentoring not exist"));
        member.removeMemberMentoring(memberMentoring);
        mentoring.removeMemberMentoring(memberMentoring);
        memberMentoringRepository.delete(memberMentoring);
        return member.getMemberId();
    }
}
