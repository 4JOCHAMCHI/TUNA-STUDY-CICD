package com.team4chamchi.tunastudycicd.studyroom.dto;

import com.team4chamchi.tunastudycicd.studyroom.aggregate.StudyRoom;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class StudyRoomDTO {
    private int roomId;
    private String roomName;

    public StudyRoomDTO(StudyRoom studyRoom) {
        this.roomId = studyRoom.getRoomId();
        this.roomName = studyRoom.getRoomName();
    }
}
