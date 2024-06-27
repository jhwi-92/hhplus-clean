package com.hhplus.cleanarchitecture.dto;

import com.hhplus.cleanarchitecture.entity.LectureApply;
import com.hhplus.cleanarchitecture.entity.LectureSchedule;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class LectureApplyResponseDto {

    private Long id;
    private Long lectureScheduleId;
    private Long userId;
    private String status;

    public LectureApplyResponseDto(Long id, Long lectureScheduleId, Long userId, String status) {
        this.id = id;
        this.lectureScheduleId = lectureScheduleId;
        this.userId = userId;
        this.status = status;
    }

    public static LectureApplyResponseDto fromEntity(LectureApply lectureApply) {
        return new LectureApplyResponseDto(
            lectureApply.getId(),
            lectureApply.getLectureScheduleId(),
            lectureApply.getUserId(),
            lectureApply.getStatus()
        );
    }

    public static List<LectureApplyResponseDto> fromEntities(List<LectureApply> lectureApply) {
        return lectureApply.stream()
            .map(LectureApplyResponseDto::fromEntity)
            .collect(Collectors.toList());
    }
}
