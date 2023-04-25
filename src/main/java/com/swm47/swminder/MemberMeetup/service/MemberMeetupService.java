package com.swm47.swminder.MemberMeetup.service;

import com.swm47.swminder.Meetup.entity.Meetup;
import com.swm47.swminder.Meetup.entity.MeetupDTO;
import com.swm47.swminder.Meetup.repository.MeetupRepository;
import com.swm47.swminder.Member.entity.Member;
import com.swm47.swminder.Member.repository.MemberRepository;
import com.swm47.swminder.MemberMeetup.entity.MemberMeetup;
import com.swm47.swminder.MemberMeetup.repository.MemberMeetupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberMeetupService {

    private final MemberMeetupRepository memberMeetupRepository;

    private final MemberRepository memberRepository;

    private final MeetupRepository meetupRepository;

    public List<MeetupDTO> getLists(Long memberId) {
        List<MemberMeetup> memberMeetups = memberMeetupRepository.findMemberMeetupByMemberId(memberId);
        List<MeetupDTO> meetupDTOs = new ArrayList<>();
        memberMeetups.forEach(memberMeetup -> {
            Optional<Meetup> res = meetupRepository.findById(memberMeetup.getMeetup().getMeetupId());
            Meetup meetup = res.get();
            meetupDTOs.add(meetup.toDTO());
        });

        return meetupDTOs;
    }

    public MeetupDTO getList(Long meetupId) {
        Optional<Meetup> res = meetupRepository.findById(meetupId);
        Meetup meetup = res.get();
        return meetup.toDTO();
    }

    public Long saveMemberMeetup(Long memberId, Long meetupId) {
        Optional<Member> resMember = memberRepository.findById(memberId);
        Member member = resMember.get();
        Optional<Meetup> resMeetup = meetupRepository.findById(meetupId);
        Meetup meetup = resMeetup.get();

        MemberMeetup memberMeetup = new MemberMeetup();
        memberMeetup.addMember(member); memberMeetup.addMeetup(meetup);

        MemberMeetup savedMemberMeetup = memberMeetupRepository.save(memberMeetup);

        return savedMemberMeetup.getMemberMeetupId();
    }

    public void deleteMeetup(Long memberId, Long meetupId) {
        Optional<Member> resMember = memberRepository.findById(memberId);
        Member member = resMember.get();
        Optional<Meetup> resMeetup = meetupRepository.findById(meetupId);
        Meetup meetup = resMeetup.get();

        Optional<MemberMeetup> resMemberMeetup = memberMeetupRepository.findMemberMeetupById(memberId, meetupId);
        MemberMeetup memberMeetup = resMemberMeetup.get();

        member.deleteMemberMeetup(memberMeetup);
        meetup.deleteMemberMeetup(memberMeetup);

        memberMeetupRepository.deleteById(memberMeetup.getMemberMeetupId());
    }
}
