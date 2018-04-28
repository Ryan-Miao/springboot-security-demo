package com.test.springsecuritydemo.domain.entity;

import com.test.springsecuritydemo.utils.SuppressFBWarnings;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@SuppressFBWarnings("EI_EXPOSE_REP")
@Data
@Entity
public class SysRole {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


}
