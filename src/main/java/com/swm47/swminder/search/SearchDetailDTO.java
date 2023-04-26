package com.swm47.swminder.search;

import com.swm47.swminder.Board.entity.BoardDTO;
import com.swm47.swminder.Meetup.entity.MeetupDTO;
import com.swm47.swminder.Mentoring.entity.MentoringDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
public class SearchDetailDTO {
    List<BoardDTO> board = new ArrayList<>();
    List<MeetupDTO> meetup = new ArrayList<>();
    List<MentoringDTO> mentoring = new ArrayList<>();
}
