package com.swm47.swminder.Mentoring.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentoringDTO {

    private Long mentoringId;

    private String title;

    private String category;

    private String author;

    private LocalDateTime createdDate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime applyStartTime;

    private LocalDateTime applyEndTime;

    private int joinCount;

    private int limitCount;

    private int qustnrSn;
}
