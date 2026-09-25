package com.airtribe.trustdesk.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Trace {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;
    public Long ticketId;
    public String runType;

    @Column(length=3000)

    public String retrievedDocIds;

    @Column(length=2000)
    public String toolActions;
    public String guardrailResult;
    public String finalStatus;
    public LocalDateTime createdAt=LocalDateTime.now();
}
