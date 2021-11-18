package com.example.CommerceReservation.domain;
import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


@Entity
@Table
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_cubicle_id")
    private Cubicle cubicle;

    @Column(nullable = false)
    private Date dateIn;

    @Column(nullable = false)
    private Date dateOut;

    @Column(nullable = false, length = 10)
    private String status;

    @Column(name = "reservation_employee_id")
    private long userId;

    public Reservation(Cubicle cubicle, Date dateIn, Date dateOut, String status, long userId){
        this.cubicle = cubicle;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public String getStatus() {
        return status;
    }

    public long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
