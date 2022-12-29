package com.exam.examserver.model.user;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long userRoleId;

    //user
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter
    @Setter
    private User user;

    //role
    @ManyToOne
    @Getter
    @Setter
    private Role role;
}
