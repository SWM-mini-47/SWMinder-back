package com.swm47.swminder.Mentoring.service;

import com.swm47.swminder.Meetup.entity.Meetup;
import com.swm47.swminder.Meetup.entity.MeetupDTO;
import com.swm47.swminder.Mentoring.entity.Mentoring;
import com.swm47.swminder.Mentoring.entity.MentoringDTO;
import com.swm47.swminder.Mentoring.repository.MentoringRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentoringService {

    private final MentoringRepository mentoringRepository;

    @Transactional
    public Long upsert(Mentoring mentoring) {
//        log.info("MentoringService.upsert");
        Mentoring foundMentoring = mentoringRepository.findByQustnrSn(mentoring.getQustnrSn()).orElseGet(Mentoring::new);
        foundMentoring.overwrite(mentoring);
        if (foundMentoring.getMentoringId() == null) {
//            log.info("\tinsert mentoring");
            mentoringRepository.save(foundMentoring);
        } else {
//            log.info("\toverwrite mentoring");
        }
        return foundMentoring.getMentoringId();
    }


    public List<MentoringDTO> getMeetupsYearAndMonth(Long year, Long month) {
        List<Mentoring> mentorings = mentoringRepository.findByYearAndMonth(year, month);
        List<MentoringDTO> mentoringDTOs = new ArrayList<>();
        mentorings.forEach(mentoring -> {
            MentoringDTO mentoringDTO = mentoring.toDTO();
            mentoringDTOs.add(mentoringDTO);
        });

        return mentoringDTOs;
    }

    public List<MentoringDTO> getMeetupsYearAndMonthLimit4(Long year, Long month) {
        Pageable pageable = PageRequest.of(0, 4);
        List<Mentoring> mentorings = mentoringRepository.findByYearAndMonthLimit4(year, month, pageable);
        List<MentoringDTO> mentoringDTOs = new ArrayList<>();
        mentorings.forEach(mentoring -> {
            MentoringDTO mentoringDTO = mentoring.toDTO();
            mentoringDTOs.add(mentoringDTO);
        });

        return mentoringDTOs;
    }
    public List<MentoringDTO> getMeetupsYearAndMonthAndDay(Long year, Long month, Long day) {
        List<Mentoring> mentorings = mentoringRepository.findByYearAndMonthAndDay(year, month, day);
        List<MentoringDTO> mentoringDTOs = new ArrayList<>();
        mentorings.forEach(mentoring -> {
            MentoringDTO mentoringDTO = mentoring.toDTO();
            mentoringDTOs.add(mentoringDTO);
        });

        return mentoringDTOs;
    }

    public List<MentoringDTO> getMeetupsYearAndMonthAndDayLimit4(Long year, Long month, Long day) {
        Pageable pageable = PageRequest.of(0, 4);
        List<Mentoring> mentorings = mentoringRepository.findByYearAndMonthAndDayLimit4(year, month, day, pageable);
        List<MentoringDTO> mentoringDTOs = new ArrayList<>();
        mentorings.forEach(mentoring -> {
            MentoringDTO mentoringDTO = mentoring.toDTO();
            mentoringDTOs.add(mentoringDTO);
        });

        return mentoringDTOs;
    }
    public List<Mentoring> findAll() {
        return (List<Mentoring>) mentoringRepository.findAll();
    }

}
