package com.airtribe.trustdesk.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface Base <T,ID> extends JpaRepository<T,ID>{

}
