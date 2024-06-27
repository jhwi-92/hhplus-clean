package com.hhplus.cleanarchitecture.dto;

import com.hhplus.cleanarchitecture.entity.LectureApply;
import com.hhplus.cleanarchitecture.entity.LectureSchedule;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class LectureResponseDto {

    private Long id;
    private Long lectureId;
    private Integer recruitCount;
    private LocalDateTime openDate;
    private LocalDateTime createdAt;

    public LectureResponseDto(Long id, Long lectureId, Integer recruitCount, LocalDateTime openDate, LocalDateTime createdAt) {
        this.id = id;
        this.lectureId = lectureId;
        this.recruitCount = recruitCount;
        this.openDate = openDate;
        this.createdAt = createdAt;
    }


    public static LectureResponseDto fromEntity(LectureSchedule lectureSchedule) {
        return new LectureResponseDto(
            lectureSchedule.getId(),
            lectureSchedule.getLectureId(),
            lectureSchedule.getRecruitCount(),
            lectureSchedule.getOpenDate(),
            lectureSchedule.getCreateAt()
        );
    }

    public static List<LectureResponseDto> fromEntities(List<LectureSchedule> lectureSchedules) {
        return lectureSchedules.stream()
            .map(LectureResponseDto::fromEntity)
            .collect(Collectors.toList());
    }


}
