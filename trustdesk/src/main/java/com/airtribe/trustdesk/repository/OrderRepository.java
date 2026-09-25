package com.airtribe.trustdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airtribe.trustdesk.entity.CustomerOrder;

public interface OrderRepository
        extends JpaRepository<CustomerOrder,Long> {

}
