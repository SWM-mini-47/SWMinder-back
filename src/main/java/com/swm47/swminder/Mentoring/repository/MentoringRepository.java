package com.swm47.swminder.Mentoring.repository;

import com.swm47.swminder.Meetup.entity.Meetup;
import com.swm47.swminder.Mentoring.entity.Mentoring;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MentoringRepository extends CrudRepository<Mentoring, Long> {
    Optional<Mentoring> findByQustnrSn(int qustnrSn);

    @Query("select m from Mentoring m where cast(year(m.startTime) as long) = :year" +
            " and cast(month(m.startTime) as long) = :month " +
            "order by m.startTime desc")
    List<Mentoring> findByYearAndMonthLimit4(@Param("year") Long year, @Param("month") Long month, Pageable pageable);

    @Query("select m from Mentoring m where cast(year(m.startTime) as long) = :year" +
            " and cast(month(m.startTime) as long) = :month " +
            "order by m.startTime desc")
    List<Mentoring> findByYearAndMonth(@Param("year") Long year, @Param("month") Long month);

    @Query("select m from Mentoring m where cast(year(m.startTime) as long) = :year " +
            "and cast(month(m.startTime) as long) = :month " +
            "and cast(day(m.startTime) as long) = :day " +
            "order by m.startTime desc")
    List<Mentoring> findByYearAndMonthAndDayLimit4(@Param("year") Long year, @Param("month") Long month,
                                                   @Param("day") Long day, Pageable pageable);

    @Query("select m from Mentoring m where cast(year(m.startTime) as long) = :year " +
            "and cast(month(m.startTime) as long) = :month " +
            "and cast(day(m.startTime) as long) = :day " +
            "order by m.startTime desc")
    List<Mentoring> findByYearAndMonthAndDay(@Param("year") Long year, @Param("month") Long month, @Param("day") Long day);
}
