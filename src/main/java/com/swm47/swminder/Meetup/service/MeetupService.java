package com.swm47.swminder.Meetup.service;

import com.swm47.swminder.Meetup.entity.Meetup;
import com.swm47.swminder.Meetup.entity.MeetupDTO;
import com.swm47.swminder.Meetup.repository.MeetupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetupService {

    private final MeetupRepository meetupRepository;

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

}
