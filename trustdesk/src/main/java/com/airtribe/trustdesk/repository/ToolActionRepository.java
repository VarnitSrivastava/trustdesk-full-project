package com.airtribe.trustdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airtribe.trustdesk.entity.ToolAction;

public interface ToolActionRepository
        extends JpaRepository<ToolAction,Long> {

}
