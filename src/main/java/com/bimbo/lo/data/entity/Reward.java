package com.bimbo.lo.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_reward")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "reward_name", unique = true, nullable = false)
    private String name;
    @Column(name = "reward_value", nullable = false)
    private Integer value;

}
