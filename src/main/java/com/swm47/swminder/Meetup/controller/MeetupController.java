package com.swm47.swminder.Meetup.controller;

import com.swm47.swminder.Comment.entity.Comment;
import com.swm47.swminder.Comment.entity.CommentDTO;
import com.swm47.swminder.Comment.service.CommentService;
import com.swm47.swminder.Meetup.entity.MeetupDTO;
import com.swm47.swminder.Meetup.service.MeetupService;
import com.swm47.swminder.Member.entity.MemberDTO;
import com.swm47.swminder.Member.service.MemberService;
import com.swm47.swminder.MemberMeetup.service.MemberMeetupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meetup")
public class MeetupController {

    private final MeetupService meetupService;

    private final CommentService commentService;

    private final MemberService memberService;

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

    @PostMapping("/list/{id}/save/member")
    public ResponseEntity<Long> saveMember(@PathVariable("id") Long meetupId) {
        MemberDTO memberDTO = memberService.findMember();
        Long memberId = memberDTO.getMemberId();
        Long memberMeetupId = memberMeetupService.saveMemberMeetup(memberId, meetupId);
        return new ResponseEntity<>(memberMeetupId, HttpStatus.OK);
    }

    @PostMapping("/list/{id}/save/comment")
    public ResponseEntity<Long> saveComment(@PathVariable("id") Long meetupId,
                                            @RequestBody CommentDTO commentDTO) {
        Long Id = commentService.saveMeetupComment(meetupId, commentDTO);
        return new ResponseEntity<>(Id, HttpStatus.OK);
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
    @GetMapping("/save")
    public ResponseEntity<String> saveComment() {
        return new ResponseEntity<>("OK" , HttpStatus.OK);
    }
}
