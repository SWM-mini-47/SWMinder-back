package com.swm47.swminder.search;

import com.swm47.swminder.Board.entity.BoardDTO;
import com.swm47.swminder.Meetup.entity.MeetupDTO;
import com.swm47.swminder.Mentoring.entity.MentoringDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchDTO {
    List<String> board = new ArrayList<>();
    List<String> meetup = new ArrayList<>();
    List<String> mentoring = new ArrayList<>();
}
