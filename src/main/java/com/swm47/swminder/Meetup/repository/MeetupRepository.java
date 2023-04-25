package com.swm47.swminder.Meetup.repository;

import com.swm47.swminder.Meetup.entity.Meetup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetupRepository extends JpaRepository<Meetup, Long> {
}
