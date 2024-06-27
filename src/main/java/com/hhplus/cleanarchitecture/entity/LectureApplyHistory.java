package com.hhplus.cleanarchitecture.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "LECTURE_APPLY_HISTORY")
public class LectureApplyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "LECTURE_APPLY_ID", nullable = false)
    private Long lectureApplyId;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "CREATE_AT", nullable = false)
    private LocalDateTime createAt;

    // 매개변수 있는 생성자


    public LectureApplyHistory(Long lectureApplyId, String status) {
        this.lectureApplyId = lectureApplyId;
        this.status = status;
        this.createAt = LocalDateTime.now();
    }
}