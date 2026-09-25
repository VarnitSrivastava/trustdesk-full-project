package com.airtribe.trustdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airtribe.trustdesk.entity.Ticket;

 public interface TicketRepository
         extends JpaRepository<Ticket,Long> {

 }
