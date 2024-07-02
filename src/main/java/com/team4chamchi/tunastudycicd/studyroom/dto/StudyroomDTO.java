package com.team4chamchi.tunastudycicd.studyroom.dto;

import com.team4chamchi.tunastudycicd.studyroom.aggregate.Studyroom;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class StudyroomDTO {
    private int roomId;
    private String roomName;

    public StudyroomDTO(Studyroom studyRoom) {
        this.roomId = studyRoom.getRoomId();
        this.roomName = studyRoom.getRoomName();
    }
}
