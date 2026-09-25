package com.airtribe.trustdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airtribe.trustdesk.entity.Draft;

public interface DraftRepository
        extends JpaRepository<Draft,Long> {

}
