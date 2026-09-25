package com.airtribe.trustdesk.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity

@Table(name="customer_orders")

public class CustomerOrder {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;
    public Long customerId;
    public String item;
    public BigDecimal amount;
    public String status;
    public LocalDateTime orderedAt;
    public CustomerOrder(){

    }
}
