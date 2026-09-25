package com.airtribe.trustdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airtribe.trustdesk.entity.Customer;

public interface CustomerRepository
        extends JpaRepository<Customer,Long> {

}
