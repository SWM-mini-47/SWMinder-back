package com.swm47.swminder.Meetup.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetupDTO {

    private Long meetupId;

    private String title;

    private String category;

    private String author;

    private LocalDateTime createdDate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public Meetup toEntity() {
        return Meetup.builder()
                .meetupId(meetupId)
                .title(title)
                .category(category)
                .author(author)
                .createdDate(createdDate)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
