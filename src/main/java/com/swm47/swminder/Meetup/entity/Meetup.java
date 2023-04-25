package com.swm47.swminder.Meetup.entity;

import com.swm47.swminder.Comment.entity.Comment;
import com.swm47.swminder.MemberMeetup.entity.MemberMeetup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.DataTruncation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Meetup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetupId;

    private String title;

    private String category;

    private String author;

    @CreatedDate
    private LocalDateTime createdDate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public MeetupDTO toDTO() {
        return MeetupDTO.builder()
                    .meetupId(meetupId)
                    .title(title)
                    .category(category)
                    .author(author)
                    .createdDate(createdDate)
                    .startTime(startTime)
                    .endTime(endTime)
                    .build();
    }

    public void updateMeetup(String title, String category, String author,
                             LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.category = category;
        this.author = author;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @OneToMany(mappedBy = "meetup", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "meetup", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberMeetup> memberMeetups = new ArrayList<>();

    public void deleteMemberMeetup(MemberMeetup memberMeetup) {
        memberMeetups.remove(memberMeetup);
        memberMeetup.setMeetup(null);
    }
}