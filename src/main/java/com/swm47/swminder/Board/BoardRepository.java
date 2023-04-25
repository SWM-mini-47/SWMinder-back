package com.swm47.swminder.Board;

import com.swm47.swminder.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where year(b.createdDate) = :year and month(b.createdDate) = :month " +
            "order by b.createdDate desc")
    List<Board> findByYearAndMonth(@Param("year") Long year, @Param("month") Long month);

    @Query("select b from Board b where year(b.createdDate) = :year and month(b.createdDate) = :month and day(b.createdDate) = :day " +
            "order by b.createdDate desc")
    List<Board> findByYearAndMonthAndDay(@Param("year") Long year, @Param("month") Long month, @Param("day") Long day);

}
