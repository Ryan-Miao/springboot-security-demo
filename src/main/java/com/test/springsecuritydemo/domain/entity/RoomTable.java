package com.test.springsecuritydemo.domain.entity;

import com.test.springsecuritydemo.utils.SuppressFBWarnings;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 * Created by Ryan Miao on 12/2/17.
 */
@Data
@SuppressFBWarnings("EI_EXPOSE_REP")
@Entity(name = "room")
public class RoomTable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column
    private String comment;

    @Column(updatable = false)
    private Date createDate;

    @Column
    private Date updateDate;

    public RoomTable() {
    }

    public RoomTable(String name, String comment, Date createDate, Date updateDate) {
        this.name = name;
        this.comment = comment;
        this.createDate = (Date) createDate.clone();
        this.updateDate = (Date) updateDate.clone();
    }


    public RoomTable(Integer id, String name, String comment, Date updateDate) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.updateDate = (Date) updateDate.clone();
    }

}
