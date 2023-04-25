package com.swm47.swminder.Member.controller;

import com.swm47.swminder.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
