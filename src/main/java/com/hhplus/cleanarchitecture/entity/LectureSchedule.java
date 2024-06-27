package com.hhplus.cleanarchitecture.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "LECTURE_SCHEDULE")
public class LectureSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "LECTURE_ID", nullable = false)
    private Long lectureId;

    @Column(name = "RECRUIT_COUNT", nullable = false)
    private Integer recruitCount;

    @Column(name = "APPLY_COUNT", nullable = false)
    private Integer applyCount;

    @Column(name = "OPEN_DATE", nullable = false)
    private LocalDateTime openDate;

    @Column(name = "CREATE_AT", nullable = false)
    private LocalDateTime createAt;


    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }
}