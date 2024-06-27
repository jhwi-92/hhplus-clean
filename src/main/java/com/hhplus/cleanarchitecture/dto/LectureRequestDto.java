package com.hhplus.cleanarchitecture.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class LectureRequestDto {

    @NotNull
    private Long lectureScheduleId;

    @NotNull
    private Long userId;
}
