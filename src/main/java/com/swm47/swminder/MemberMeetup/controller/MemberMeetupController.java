package com.swm47.swminder.MemberMeetup.controller;

import com.swm47.swminder.Meetup.entity.MeetupDTO;
import com.swm47.swminder.Meetup.service.MeetupService;
import com.swm47.swminder.Member.entity.MemberDTO;
import com.swm47.swminder.Member.service.MemberService;
import com.swm47.swminder.MemberMeetup.service.MemberMeetupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/membermeetup")
@Slf4j
public class MemberMeetupController {

    private final MemberService memberService;

    private final MeetupService meetupService;

    private final MemberMeetupService memberMeetupService;

    @GetMapping("/lists")
    public ResponseEntity<List<MeetupDTO>> getLists() {
        MemberDTO memberDTO = memberService.findMember();
        Long memberId = memberDTO.getMemberId();
        List<MeetupDTO> lists = memberMeetupService.getLists(memberId);
        return new ResponseEntity<>(lists, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<MeetupDTO> getList(@PathVariable("id") Long meetupId) {
        MeetupDTO meetupDTO = memberMeetupService.getList(meetupId);
        return new ResponseEntity<>(meetupDTO, HttpStatus.OK);
    }

    @PostMapping("/list/{id}/save")
    public ResponseEntity<Long> saveMember(@PathVariable("id") Long meetupId) {
        MemberDTO memberDTO = memberService.findMember();
        Long memberId = memberDTO.getMemberId();
        Long memberMeetupId = memberMeetupService.saveMemberMeetup(memberId, meetupId);
        return new ResponseEntity<>(memberMeetupId, HttpStatus.OK);
    }

    @GetMapping("/save")
    public ResponseEntity<String> saveMeetup() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Long> saveMeetup(@RequestBody MeetupDTO meetupDTO) {
        MemberDTO memberDTO = memberService.findMember();
        Long memberId = memberDTO.getMemberId();
        Long meetupId = meetupService.saveMeetup(meetupDTO);
        Long savedId = memberMeetupService.saveMemberMeetup(memberId, meetupId);

        return new ResponseEntity<>(savedId, HttpStatus.OK);
    }
    @PatchMapping("/update/{meetupId}")
    public ResponseEntity<String> updateMeetup(@PathVariable("meetupId") Long meetupId,
                                               @RequestBody MeetupDTO meetupDTO) {
        meetupService.updateMeetup(meetupId, meetupDTO);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{meetupId}")
    public ResponseEntity<String> deleteMeetup(@PathVariable("meetupId") Long meetupId) {
        MemberDTO memberDTO = memberService.findMember();
        Long memberId = memberDTO.getMemberId();
        memberMeetupService.deleteMeetup(memberId, meetupId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
