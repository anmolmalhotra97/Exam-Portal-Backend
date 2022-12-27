package com.exam.examserver.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    private Long roleId;
    private String roleName;

    //We want the Roles for the users to be fetched only when function is called, Hence LAZY
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UserRole> userRoles = new HashSet<>();

    public Role() {
    }

    public Role(Long roleId) {
        this.roleId = roleId;
    }
}
