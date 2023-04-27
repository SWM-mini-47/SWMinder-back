package com.swm47.swminder.Mentoring.entity;

import com.swm47.swminder.MemberMentoring.entity.MemberMentoring;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mentoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentoringId;

    @Column(length = 255)
    private String title;

    @Column(length = 40)
    private String category;

    @Column(length = 20)
    private String author;

    private LocalDateTime createdDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime applyStartTime;
    private LocalDateTime applyEndTime;
    private int joinCount;
    private int limitCount;

    private int qustnrSn;

    public MentoringDTO toDTO() {
        return MentoringDTO.builder()
                .mentoringId(mentoringId)
                .title(title)
                .category(category)
                .author(author)
                .createdDate(createdDate)
                .startTime(startTime)
                .endTime(endTime)
                .applyStartTime(applyStartTime)
                .applyEndTime(applyEndTime)
                .joinCount(joinCount)
                .limitCount(limitCount)
                .qustnrSn(qustnrSn)
                .build();
    }
    public void overwrite(Mentoring mentoring) {
        title = mentoring.getTitle();
        category = mentoring.getCategory();
        author = mentoring.getAuthor();
        createdDate = mentoring.getCreatedDate();
        startTime = mentoring.getStartTime();
        endTime = mentoring.getEndTime();
        applyStartTime = mentoring.getApplyStartTime();
        applyEndTime = mentoring.getApplyEndTime();
        joinCount = mentoring.getJoinCount();
        limitCount = mentoring.getLimitCount();
        qustnrSn = mentoring.getQustnrSn();
    }

    @OneToMany(mappedBy = "mentoring", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberMentoring> memberMentorings = new ArrayList<>();

    public void addMemberMentoring(MemberMentoring memberMentoring) {
        memberMentorings.add(memberMentoring);
        joinCount = memberMentorings.size();
    }

    public void removeMemberMentoring(MemberMentoring memberMentoring) {
        memberMentorings.remove(memberMentoring);
    }
}
