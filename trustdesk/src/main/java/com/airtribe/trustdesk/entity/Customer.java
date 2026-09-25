package com.airtribe.trustdesk.entity;
import jakarta.persistence.*;

@Entity

public class Customer {
    @Id @GeneratedValue
            (strategy=GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String email;
    public String phone;
    public Customer(){

    }
    public Customer(String n,String e,String p){
        name=n;
        email=e;
        phone=p;
    }
}
