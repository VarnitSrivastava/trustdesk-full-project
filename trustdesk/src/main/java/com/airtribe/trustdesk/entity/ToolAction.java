package com.airtribe.trustdesk.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity

public class ToolAction {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)

    public Long id;
    public Long ticketId;
    public String actionType;
    public String idempotencyKey;
    public String status;
    public String approvedBy;
    public LocalDateTime createdAt=LocalDateTime.now();
}
