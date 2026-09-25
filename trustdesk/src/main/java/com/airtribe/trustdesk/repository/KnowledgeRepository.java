package com.airtribe.trustdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airtribe.trustdesk.entity.KnowledgeDocument;

public interface KnowledgeRepository
        extends JpaRepository<KnowledgeDocument,String> {

}
