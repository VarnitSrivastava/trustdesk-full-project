package com.airtribe.trustdesk.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Ticket {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;
    public Long customerId;
    public Long orderId;

    @Column(length=5000)
    public String message;
    public LocalDateTime createdAt;
    public String category;
    public String priority;
    public boolean escalated;
    public String status="OPEN";
    public Ticket(){

    }
}
