package com.test.springsecuritydemo.domain.entity;

import com.test.springsecuritydemo.utils.SuppressFBWarnings;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;

@SuppressFBWarnings("EI_EXPOSE_REP")
@Data
@Entity
@Table(indexes = {@Index(name = "sys_role_id_uniq", columnList = "name", unique = true)})
public class SysRole {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


}
