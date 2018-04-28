package com.test.springsecuritydemo.domain.valueobject;

import java.util.List;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Ryan on 2017/11/16/0016.
 */
public class Room {

    private Integer roomId;
    @NotEmpty
    @Size(min = 3, max = 20, message = "The size of room name should between 3 and 20")
    private String roomName;
    private String comment;
    private List<Integer> arr;

    public Room() {
    }

    public Room(Integer roomId, String roomName, String comment, List<Integer> arr) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.comment = comment;
        this.arr = arr;
    }

    public List<Integer> getArr() {
        return arr;
    }

    public void setArr(List<Integer> arr) {
        this.arr = arr;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
