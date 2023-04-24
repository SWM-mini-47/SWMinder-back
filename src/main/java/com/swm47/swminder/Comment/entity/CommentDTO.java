package com.swm47.swminder.Comment.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long commentId;

    private String author;

    private String content;

    private LocalDateTime createdDate;

    public Comment toEntity() {
        return Comment.builder()
                .commentId(commentId)
                .author(author)
                .content(content)
                .build();
    }
}
