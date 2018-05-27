package com.test.springsecuritydemo.domain.entity;

import com.test.springsecuritydemo.constant.AuthorityEnum;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;

@SuppressFBWarnings("EI_EXPOSE_REP")
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class SysPermission {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityEnum name;


}
