package com.team4chamchi.tunastudycicd.studyroom.respository;

import com.team4chamchi.tunastudycicd.studyroom.aggregate.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRoomRepository extends JpaRepository<StudyRoom, Integer> {
}
