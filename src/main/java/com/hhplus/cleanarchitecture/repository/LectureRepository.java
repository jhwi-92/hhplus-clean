package com.hhplus.cleanarchitecture.repository;

import com.hhplus.cleanarchitecture.entity.Lecture;
import com.hhplus.cleanarchitecture.entity.LectureSchedule;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository  {

    Optional<List<LectureSchedule>> selectLectureList() throws IllegalArgumentException;

}

