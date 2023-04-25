package com.swm47.swminder.Meetup.repository;

import com.swm47.swminder.Board.entity.Board;
import com.swm47.swminder.Meetup.entity.Meetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetupRepository extends JpaRepository<Meetup, Long> {

    @Query("select m from Meetup m where year(m.createdDate) = :year and month(m.createdDate) = :month " +
            "order by m.createdDate desc")
    List<Meetup> findByYearAndMonth(@Param("year") Long year, @Param("month") Long month);

    @Query("select m from Meetup m where year(m.createdDate) = :year and month(m.createdDate) = :month and day(m.createdDate) = :day " +
            "order by m.createdDate desc")
    List<Meetup> findByYearAndMonthAndDay(@Param("year") Long year, @Param("month") Long month, @Param("day") Long day);
}
