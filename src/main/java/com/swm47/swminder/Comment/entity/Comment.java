package com.swm47.swminder.Comment.entity;

import com.swm47.swminder.Board.entity.Board;
import com.swm47.swminder.Meetup.entity.Meetup;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder @Getter
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String author;

    private String content;

    @CreatedDate
    private LocalDateTime createdDate;

    public CommentDTO toDTO() {
        return CommentDTO.builder()
                    .commentId(commentId)
                    .author(author)
                    .content(content)
                    .createdDate(createdDate)
                    .build();
    }

    public void updateComment(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    @Setter
    private Board board;
    public void addBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEETUP_ID")
    @Setter
    private Meetup meetup;

    public void addMeetup(Meetup meetup) {
        this.meetup = meetup;
        meetup.getComments().add(this);
    }
}