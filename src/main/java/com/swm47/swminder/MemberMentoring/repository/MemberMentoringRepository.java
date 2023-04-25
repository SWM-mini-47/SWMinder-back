package com.swm47.swminder.MemberMentoring.repository;

import com.swm47.swminder.MemberMentoring.entity.MemberMentoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberMentoringRepository extends JpaRepository<MemberMentoring, Long> {
    Optional<MemberMentoring> findMemberMentoringByMemberAndMentoring(Long memberId, Long mentoringId);

}
