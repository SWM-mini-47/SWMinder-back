package com.swm47.swminder.Board.entity;

import com.swm47.swminder.Comment.entity.Comment;
import com.swm47.swminder.Comment.entity.CommentDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long boardId;

    private String title;

    private String content;

    private String author;

    private LocalDateTime createdDate;

    @Builder.Default
    private List<CommentDTO> comments = new ArrayList<>();

    public Board ToEntity() {
        return Board.builder()
                .boardId(boardId)
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
