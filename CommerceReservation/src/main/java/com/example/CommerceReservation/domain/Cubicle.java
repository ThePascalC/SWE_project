package com.example.CommerceReservation.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Cubicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reservation_cubicle_id")
    private Long cubicleId;

    public long getId() {
        return id;
    }

    public Long getCubicleId() {
        return cubicleId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCubicleId(long cubicleId) {
        this.cubicleId = cubicleId;
    }
}
