package com.exam.examserver.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    private Long roleId;
    private String roleName;

    public Role() {
    }

    public Role(Long roleId) {
        this.roleId = roleId;
    }
}
