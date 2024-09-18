package com.bimbo.lo.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserWallet wallet;

    public User(Integer id) {
        this.id = id;
    }
}
