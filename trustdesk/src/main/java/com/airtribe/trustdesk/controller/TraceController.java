package com.airtribe.trustdesk.controller;
import org.springframework.web.bind.annotation.*;
import com.airtribe.trustdesk.service.TrustDeskService;
import com.airtribe.trustdesk.entity.*;
import java.util.*;
@RestController
@RequestMapping("/api/traces")
public class TraceController {
    private final TrustDeskService s;
    public TraceController(TrustDeskService s){
        this.s=s;} @GetMapping
    public List<Trace> all(){
        return s.traces();
    }
}
