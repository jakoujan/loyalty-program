package com.bimbo.lo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "tbl_user_wallet")
public class UserWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cl_user", nullable = false)
    private User user;
    @Column(name = "")
    private Integer points;

    public UserWallet(User user) {
        this.user = user;
    }

}
