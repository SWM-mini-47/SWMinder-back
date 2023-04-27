package com.swm47.swminder.Meetup.repository;

import com.swm47.swminder.Board.entity.Board;
import com.swm47.swminder.Meetup.entity.Meetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetupRepository extends JpaRepository<Meetup, Long> {

    @Query("select m from Meetup m where cast(year(m.createdDate) as long) = :year " +
            "and cast(month(m.createdDate) as long) = :month " +
            "order by m.createdDate desc")
    List<Meetup> findByYearAndMonth(@Param("year") Long year, @Param("month") Long month);
    @Query("select m from Meetup m where cast(year(m.createdDate) as long) = :year " +
            "and cast(month(m.createdDate) as long) = :month " +
            "order by m.createdDate desc limit 4")
    List<Meetup> findByYearAndMonthLimit4(@Param("year") Long year, @Param("month") Long month);


    @Query("select m from Meetup m where cast(year(m.createdDate) as long) = :year" +
            " and cast(month(m.createdDate) as long) = :month " +
            "and cast(day(m.createdDate) as long) = :day " +
            "order by m.createdDate desc")
    List<Meetup> findByYearAndMonthAndDay(@Param("year") Long year, @Param("month") Long month,
                                                @Param("day") Long day);
    @Query("select m from Meetup m where cast(year(m.createdDate) as long) = :year" +
            " and cast(month(m.createdDate) as long) = :month " +
            "and cast(day(m.createdDate) as long) = :day " +
            "order by m.createdDate desc limit 4")
    List<Meetup> findByYearAndMonthAndDayLimit4(@Param("year") Long year, @Param("month") Long month,
                                          @Param("day") Long day);
}
