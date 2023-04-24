package com.swm47.swminder.Board.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private String title;

    private String content;

    private String author;

    private LocalDateTime createdDate;

    public Board ToEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
