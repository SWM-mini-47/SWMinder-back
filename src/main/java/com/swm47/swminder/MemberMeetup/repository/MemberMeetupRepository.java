package com.swm47.swminder.MemberMeetup.repository;

import com.swm47.swminder.MemberMeetup.entity.MemberMeetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberMeetupRepository extends JpaRepository<MemberMeetup, Long> {

    @Query("select m from MemberMeetup m " +
            "where m.member.memberId = :memberId " +
            "and m.meetup.meetupId = :meetupId")
    Optional<MemberMeetup> findMemberMeetupById(Long memberId, Long meetupId);

    @Query("select m from MemberMeetup m " +
            "where m.member.memberId = :memberId")
    List<MemberMeetup> findMemberMeetupByMemberId(Long memberId);
}
