package com.swm47.swminder.search;

import com.swm47.swminder.Board.entity.BoardDTO;
import com.swm47.swminder.Board.service.BoardService;
import com.swm47.swminder.Meetup.entity.MeetupDTO;
import com.swm47.swminder.Meetup.service.MeetupService;
import com.swm47.swminder.Mentoring.entity.MentoringDTO;
import com.swm47.swminder.Mentoring.service.MentoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class searchController {

    private final BoardService boardService;

    private final MeetupService meetupService;

    private final MentoringService mentoringService;

    @GetMapping("/{year}/{month}")
    public ResponseEntity<List<SearchDTO>> getBoardDTO(@PathVariable("year") Long year,
                                                       @PathVariable("month") Long month) {
        List<SearchDTO> searchDTOs = new ArrayList<>();
        List<BoardDTO> boardDTOs = boardService.getBoardsYearAndMonth(year, month);
        List<MeetupDTO> meetupDTOs = meetupService.getMeetupsYearAndMonth(year, month);
        List<MentoringDTO> mentoringDTOs = mentoringService.getMeetupsYearAndMonth(year, month);
        boardDTOs.forEach(boardDTO -> {
            SearchDTO searchDTO = new SearchDTO("board", boardDTO.getTitle());
            searchDTOs.add(searchDTO);
        });
        meetupDTOs.forEach(meetupDTO -> {
            SearchDTO searchDTO = new SearchDTO("meetup", meetupDTO.getTitle());
            searchDTOs.add(searchDTO);
        });
        mentoringDTOs.forEach(mentoringDTO -> {
            SearchDTO searchDTO = new SearchDTO("mentoring", mentoringDTO.getTitle());
            searchDTOs.add(searchDTO);
        });

        return new ResponseEntity<>(searchDTOs, HttpStatus.OK);
    }

    @GetMapping("/{year}/{month}/{day}")
    public ResponseEntity<?> getBoardDTO(@PathVariable("year") Long year,
                                                      @PathVariable("month") Long month,
                                                      @PathVariable("day") Long day) {
        List<SearchDTO> searchDTOs = new ArrayList<>();
        List<BoardDTO> boardDTOs = boardService.getBoardsYearAndMonthAndDay(year, month, day);
        List<MeetupDTO> meetupDTOs = meetupService.getMeetupsYearAndMonthAndDay(year, month, day);
        List<MentoringDTO> mentoringDTOs = mentoringService.getMeetupsYearAndMonthAndDay(year, month, day);
        boardDTOs.forEach(boardDTO -> {
            SearchDTO searchDTO = new SearchDTO("board", boardDTO);
            searchDTOs.add(searchDTO);
        });
        meetupDTOs.forEach(meetupDTO -> {
            SearchDTO searchDTO = new SearchDTO("meetup", meetupDTO);
            searchDTOs.add(searchDTO);
        });
        mentoringDTOs.forEach(mentoringDTO -> {
            SearchDTO searchDTO = new SearchDTO("mentoring", mentoringDTO);
            searchDTOs.add(searchDTO);
        });

        return new ResponseEntity<>(searchDTOs, HttpStatus.OK);
    }
}
