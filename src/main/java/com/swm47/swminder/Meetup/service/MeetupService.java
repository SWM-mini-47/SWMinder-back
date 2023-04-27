package com.swm47.swminder.Meetup.service;

import com.swm47.swminder.Board.entity.Board;
import com.swm47.swminder.Board.entity.BoardDTO;
import com.swm47.swminder.Comment.CommentRepository;
import com.swm47.swminder.Comment.entity.Comment;
import com.swm47.swminder.Comment.entity.CommentDTO;
import com.swm47.swminder.Meetup.entity.Meetup;
import com.swm47.swminder.Meetup.entity.MeetupDTO;
import com.swm47.swminder.Meetup.repository.MeetupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetupService {

    private final MeetupRepository meetupRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public Long saveComment(Long meetupId, CommentDTO commentDTO) {
        Optional<Meetup> res = meetupRepository.findById(meetupId);
        Meetup meetup = res.get();
        Comment comment = commentDTO.toEntity();
        comment.addMeetup(meetup);
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getCommentId();
    }

    @Transactional
    public Long saveMeetup(MeetupDTO meetupDTO) {
        Meetup savedMeetup = meetupRepository.save(meetupDTO.toEntity());
        return savedMeetup.getMeetupId();
    }

    @Transactional
    public void updateMeetup(Long meetupId, MeetupDTO meetupDTO) {
        Optional<Meetup> res = meetupRepository.findById(meetupId);
        Meetup meetup = res.get();
        meetup.updateMeetup(meetupDTO.getTitle(), meetupDTO.getCategory(),
                meetupDTO.getAuthor(), meetupDTO.getStartTime(), meetupDTO.getEndTime());
    }
    public List<MeetupDTO> getMeetupsYearAndMonth(Long year, Long month) {
        List<Meetup> meetups = meetupRepository.findByYearAndMonth(year, month);
        List<MeetupDTO> meetupDTOs = new ArrayList<>();
        meetups.forEach(meetup -> {
            MeetupDTO meetupDTO = meetup.toDTO();
            meetupDTOs.add(meetupDTO);
        });

        return meetupDTOs;
    }
    public List<MeetupDTO> getMeetupsYearAndMonthLimit4(Long year, Long month) {
        Pageable pageable = PageRequest.of(0, 4);
        List<Meetup> meetups = meetupRepository.findByYearAndMonthLimit4(year, month, pageable);
        List<MeetupDTO> meetupDTOs = new ArrayList<>();
        meetups.forEach(meetup -> {
            MeetupDTO meetupDTO = meetup.toDTO();
            meetupDTOs.add(meetupDTO);
        });

        return meetupDTOs;
    }

    public List<MeetupDTO> getMeetupsYearAndMonthAndDay(Long year, Long month, Long day) {
        List<Meetup> meetups = meetupRepository.findByYearAndMonthAndDay(year, month, day);
        List<MeetupDTO> meetupDTOs = new ArrayList<>();
        meetups.forEach(meetup -> {
            MeetupDTO meetupDTO = meetup.toDTO();
            meetupDTOs.add(meetupDTO);
        });

        return meetupDTOs;
    }
    public List<MeetupDTO> getMeetupsYearAndMonthAndDayLimit4(Long year, Long month, Long day) {
        Pageable pageable = PageRequest.of(0, 4);
        List<Meetup> meetups = meetupRepository.findByYearAndMonthAndDayLimit4(year, month, day, pageable);
        List<MeetupDTO> meetupDTOs = new ArrayList<>();
        meetups.forEach(meetup -> {
            MeetupDTO meetupDTO = meetup.toDTO();
            meetupDTOs.add(meetupDTO);
        });

        return meetupDTOs;
    }
}
