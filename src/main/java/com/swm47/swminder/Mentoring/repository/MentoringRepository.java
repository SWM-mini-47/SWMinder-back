package com.swm47.swminder.Mentoring.repository;

import com.swm47.swminder.Meetup.entity.Meetup;
import com.swm47.swminder.Mentoring.entity.Mentoring;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MentoringRepository extends CrudRepository<Mentoring, Long> {
    Optional<Mentoring> findByQustnrSn(int qustnrSn);

    @Query("select m from Mentoring m where year(m.createdDate) = :year and month(m.createdDate) = :month " +
            "order by m.createdDate desc")
    List<Mentoring> findByYearAndMonth(@Param("year") Long year, @Param("month") Long month);

    @Query("select m from Mentoring m where year(m.createdDate) = :year and month(m.createdDate) = :month and day(m.createdDate) = :day " +
            "order by m.createdDate desc")
    List<Mentoring> findByYearAndMonthAndDay(@Param("year") Long year, @Param("month") Long month, @Param("day") Long day);
}
