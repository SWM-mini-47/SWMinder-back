package com.swm47.swminder.Board.entity;

import com.swm47.swminder.Comment.entity.Comment;
import com.swm47.swminder.Member.entity.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder @ToString(exclude = "member")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    private String content;

    private String author;

    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @Setter
    private Member member;

    public void addMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }
    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();
}