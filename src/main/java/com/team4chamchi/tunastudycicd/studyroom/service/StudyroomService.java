package com.team4chamchi.tunastudycicd.studyroom.service;

import com.team4chamchi.tunastudycicd.studyroom.dto.StudyRoomDTO;
import com.team4chamchi.tunastudycicd.studyroom.respository.StudyRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyRoomService {

    private final StudyRoomRepository studyRoomRepository;

    @Autowired
    public StudyRoomService(StudyRoomRepository studyRoomRepository) {
        this.studyRoomRepository = studyRoomRepository;
    }

    public List<StudyRoomDTO> findALlSeat() {
        return studyRoomRepository.findAll().stream()
                .map(StudyRoomDTO::new).toList();
    }
}
