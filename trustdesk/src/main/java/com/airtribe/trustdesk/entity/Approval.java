package com.airtribe.trustdesk.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Approval {
    @Id @GeneratedValue
            (strategy=GenerationType.IDENTITY)
    public Long id;
    public Long ticketId;
    public Long actionId;
    public String decision;
    public String approvedBy;
    public LocalDateTime createdAt=LocalDateTime.now();
}
