package com.airtribe.trustdesk.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Draft {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)

    public Long id;
    public Long ticketId;
    @Column(length=8000)
    public String body;
    @Column(length=2000)
    public String citations;
    public boolean grounded;
    public LocalDateTime createdAt=LocalDateTime.now();
}
