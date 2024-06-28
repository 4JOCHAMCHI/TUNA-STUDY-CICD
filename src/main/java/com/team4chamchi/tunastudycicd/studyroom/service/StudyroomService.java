package com.team4chamchi.tunastudycicd.studyroom.service;

import com.team4chamchi.tunastudycicd.studyroom.entity.Studyroom;
import com.team4chamchi.tunastudycicd.studyroom.repository.StudyroomRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyroomService {

    private final StudyroomRepository studyroomRepository;

    @Autowired
    public StudyroomService(StudyroomRepository studyroomRepository) {
        this.studyroomRepository = studyroomRepository;
    }

    public List<Studyroom> findStudyRoomList() {
        List<Studyroom> studyroomList = studyroomRepository.findAll();

        return studyroomList;
    }
}
