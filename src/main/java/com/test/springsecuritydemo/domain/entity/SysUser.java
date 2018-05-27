package com.test.springsecuritydemo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "SE_BAD_FIELD",
    "SE_NO_SERIALVERSIONID"}, justification = "忽略可变date返回值")
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class SysUser implements UserDetails {

    private static final long serialVersionUID = 6146350055239552690L;

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @Column(columnDefinition = "timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date lastPasswordResetDate;
    private boolean enabled;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<SysRole> role;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
        return this.getRole().stream()
            .flatMap(role -> role.getPermission().stream())
            .map(permission -> new SimpleGrantedAuthority(permission.getName().name()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
