package com.example.CommerceReservation.domain;
import javax.persistence.*;

@Entity
@Table
public class Cubicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reservation_cubicle_id")
    private long cubicleId;

}
