package com.hhplus.cleanarchitecture.controller;


import com.hhplus.cleanarchitecture.dto.LectureApplyResponseDto;
import com.hhplus.cleanarchitecture.dto.LectureRequestDto;
import com.hhplus.cleanarchitecture.dto.LectureResponseDto;
import com.hhplus.cleanarchitecture.service.LectureService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    //특강목록을 조회한다.
    @GetMapping
    public ResponseEntity<List<LectureResponseDto>> selectLectureScheduleList() {

        List<LectureResponseDto> responseDto = lectureService.selectLectureScheduleList();
        return ResponseEntity.ok(responseDto);
    }

    //특강신청내역을 조회한다.
    @GetMapping("/application/{userId}")
    public ResponseEntity<List<LectureApplyResponseDto>> selectLectureApplication(@PathVariable long userId) {

        List<LectureApplyResponseDto> responseDto = lectureService.selectLectureApplication(userId);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/apply")
    public ResponseEntity<LectureResponseDto> applyLecture(@RequestBody @Valid LectureRequestDto requestDto) {

        LectureResponseDto lectureResponseDto = lectureService.applyLecture(requestDto);
        return ResponseEntity.ok(lectureResponseDto);
    }

}
