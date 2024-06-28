package com.team4chamchi.tunastudycicd.studyroom.controller;

import com.team4chamchi.tunastudycicd.studyroom.entity.Studyroom;
import com.team4chamchi.tunastudycicd.studyroom.service.StudyroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudyRoomController {

    private final StudyroomService studyroomService;

    @Autowired
    public StudyRoomController(StudyroomService studyroomService) {
        this.studyroomService = studyroomService;
    }

    @GetMapping("/studyroom")
    public List<Studyroom> findStudyRoomList() {
        return studyroomService.findStudyRoomList();
    }
}
