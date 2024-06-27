package com.hhplus.cleanarchitecture.service;

import com.hhplus.cleanarchitecture.dto.LectureApplyResponseDto;
import com.hhplus.cleanarchitecture.dto.LectureRequestDto;
import com.hhplus.cleanarchitecture.dto.LectureResponseDto;
import com.hhplus.cleanarchitecture.entity.LectureApply;
import com.hhplus.cleanarchitecture.entity.LectureApplyHistory;
import com.hhplus.cleanarchitecture.entity.LectureSchedule;
import com.hhplus.cleanarchitecture.repository.LectureApplyHistoryRepository;
import com.hhplus.cleanarchitecture.repository.LectureApplyRepository;
import com.hhplus.cleanarchitecture.repository.LectureScheduleRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LectureService {

    private final LectureScheduleRepository lectureScheduleRepository;
    private final LectureApplyRepository lectureApplyRepository;
    private final LectureApplyHistoryRepository lectureApplyHistoryRepository;

    public List<LectureResponseDto> selectLectureScheduleList() {
        List<LectureSchedule> lectureSchedules = lectureScheduleRepository.findAll();
        return LectureResponseDto.fromEntities(lectureSchedules);
    }

    public List<LectureApplyResponseDto> selectLectureApplication(long userId) {
        List<LectureApply> lectureApply = lectureApplyRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("특강신청이 없습니다."));
        return LectureApplyResponseDto.fromEntities(lectureApply);
    }


    @Transactional
    public LectureResponseDto applyLecture(LectureRequestDto requestDto) {

        //1. 해당 강의가 있는지, 수강인원이 다 안찼는지 확인한다.
        //2. 수강신청한다.
        //3. 성공인지 실패인지 확인
        //3. 히스토리 저장한다.

        LectureSchedule lectureSchedule = lectureScheduleRepository.findByIdWithPessimisticLock(requestDto.getLectureScheduleId()).orElseThrow(() -> new IllegalArgumentException("강의스케줄이 존재하지 않습니다."));

        int recruitCount = lectureSchedule.getRecruitCount();
        int applyCount = lectureSchedule.getApplyCount();


        //수강신청인원이 초과되었는지 체크한다.
        if (applyCount < recruitCount) {
            //이미 신청한 수강인지 체크한다.
            if (lectureApplyRepository.findByUserId(requestDto.getUserId()).isPresent()) {
                throw new IllegalArgumentException("이미 신청한 특강 스케줄");
            }
            //수강신청
            LectureApply lectureApply = new LectureApply(requestDto.getLectureScheduleId(),
                requestDto.getUserId(), "OK");

            lectureApplyRepository.save(lectureApply);
            lectureScheduleRepository.updateApplyCountById(requestDto.getLectureScheduleId(),
                lectureSchedule.getApplyCount()+1);

            LectureApplyHistory lectureApplyHistory = new LectureApplyHistory(
                lectureApply.getId(), lectureApply.getStatus());
            //히스토리 저장
            lectureApplyHistoryRepository.save(lectureApplyHistory);
        }
        return LectureResponseDto.fromEntity(lectureSchedule);
    }
}
