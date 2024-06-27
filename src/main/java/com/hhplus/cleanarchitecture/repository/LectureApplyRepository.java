package com.hhplus.cleanarchitecture.repository;

import com.hhplus.cleanarchitecture.entity.LectureApply;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LectureApplyRepository extends JpaRepository<LectureApply, Long> {

    Optional<List<LectureApply>> findByUserId(long userId);

    int countByLectureScheduleIdAndStatus(Long lectureScheduleId, String status);

    Optional<LectureApply> findByUserIdAndStatus(Long userId, String status);

}
