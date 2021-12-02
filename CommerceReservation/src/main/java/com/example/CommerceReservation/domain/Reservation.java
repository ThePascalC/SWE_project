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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_cubicle_id", referencedColumnName = "reservation_cubicle_id")
    private Cubicle cubicle;

    @Column(nullable = false)
    private Date dateIn;

    @Column(nullable = false)
    private Date dateOut;

    @Column(name = "reservation_employee_id")
    private long userId;

    public Reservation(){

    }
    public Reservation(Date dateIn, Date dateOut){
        this.dateIn = dateIn;
        this.dateOut = dateOut;
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


    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Cubicle getCubicle(){
        return cubicle;
    }
    public void setCubicle(Cubicle cubicle){
        this.cubicle = cubicle;
    }
}
