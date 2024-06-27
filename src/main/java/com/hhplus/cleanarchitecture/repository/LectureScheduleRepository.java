package com.hhplus.cleanarchitecture.repository;

import com.hhplus.cleanarchitecture.entity.LectureSchedule;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureScheduleRepository extends JpaRepository<LectureSchedule, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // 어노테이션 추가
    Optional<LectureSchedule> findByIdWithPessimisticLock(Long id);

    LectureSchedule updateApplyCountById(Long lectureScheduleId, int newApplyCount);
}
