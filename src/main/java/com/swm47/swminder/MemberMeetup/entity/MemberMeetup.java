package com.swm47.swminder.MemberMeetup.entity;

import com.swm47.swminder.Meetup.entity.Meetup;
import com.swm47.swminder.Member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @ToString
public class MemberMeetup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMeetupId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void addMember(Member member) {
        this.member = member;
        member.getMemberMeetups().add(this);
    }

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEETUP_ID")
    private Meetup meetup;

    public void addMeetup(Meetup meetup) {
        this.meetup = meetup;
        meetup.getMemberMeetups().add(this);
    }
}
