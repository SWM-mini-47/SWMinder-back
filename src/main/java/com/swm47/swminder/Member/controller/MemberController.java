package com.swm47.swminder.Member.controller;

import com.swm47.swminder.Member.entity.MemberDTO;
import com.swm47.swminder.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/{memberId}/mentoring/join/{mentoringId}")
    public ResponseEntity<Long> joinMentoring(@PathVariable Long memberId, @PathVariable Long mentoringId) {
        memberService.joinMentoring(memberId, mentoringId);
        return ResponseEntity.ok(memberId);
    }
    @PostMapping("/{memberId}/mentoring/unjoin/{mentoringId}")
    public ResponseEntity<Long> unJoinMentoring(@PathVariable Long memberId, @PathVariable Long mentoringId) {
        memberService.unJoinMentoring(memberId, mentoringId);
        return ResponseEntity.ok(memberId);
    }
    @GetMapping("/info")
    public ResponseEntity<MemberDTO> getLogginedInfo() {
        return ResponseEntity.ok(memberService.findMember());
    }

    @GetMapping("/info/by-id/{memberId}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @GetMapping("/info/by-login-id/{memberLoginId}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable String memberLoginId) {
        return ResponseEntity.ok(memberService.findMemberByLoginId(memberLoginId));
    }
}
