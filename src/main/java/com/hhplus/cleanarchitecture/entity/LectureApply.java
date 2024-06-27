package com.hhplus.cleanarchitecture.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

@Entity
@Data
@NoArgsConstructor
@Table(name = "LECTURE_APPLY")
public class LectureApply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "LECTURE_SCHEDULE_ID", nullable = false)
    private Long lectureScheduleId;

    @JoinColumn(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "CREATE_AT", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AT", nullable = false)
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }
    // Getters and Setters


    public LectureApply(Long lectureScheduleId, Long userId, String status) {

        this.lectureScheduleId = lectureScheduleId;
        this.userId = userId;
        this.status = status;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}