package com.airtribe.trustdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airtribe.trustdesk.entity.Approval;

public interface ApprovalRepository
        extends JpaRepository<Approval,Long> {

}
