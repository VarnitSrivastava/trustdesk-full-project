package com.airtribe.trustdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airtribe.trustdesk.entity.Trace;

public interface TraceRepository
        extends JpaRepository<Trace,Long> {

}
