package com.swm47.swminder.Board;

import com.swm47.swminder.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where cast(year(b.createdDate) as long) = :year " +
            "and cast(month(b.createdDate) as long) = :month " +
            "order by b.createdDate desc")
    List<Board> findByYearAndMonth(@Param("year") Long year, @Param("month") Long month);

    @Query("select b from Board b where cast(year(b.createdDate) as long) = :year" +
            " and cast(month(b.createdDate) as long) = :month " +
            "and cast(day(b.createdDate) as long) = :day " +
            "order by b.createdDate desc")
    List<Board> findByYearAndMonthAndDay(@Param("year") Long year, @Param("month") Long month, @Param("day") Long day);

}
