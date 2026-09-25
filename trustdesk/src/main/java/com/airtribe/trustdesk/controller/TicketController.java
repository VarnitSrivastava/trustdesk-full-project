package com.airtribe.trustdesk.controller;
import org.springframework.web.bind.annotation.*;
import com.airtribe.trustdesk.service.TrustDeskService;
import java.util.*;
import com.airtribe.trustdesk.entity.*;
@RestController
@RequestMapping
        ("/api/tickets")
public class TicketController {
    private final TrustDeskService s;
    public TicketController(TrustDeskService s){
        this.s=s;}
    @GetMapping

    public List<Ticket> all(){
        return s.list();}
    @GetMapping
            ("/{id}")
    public Ticket one(
            @PathVariable Long id){
        return s.get(id);}
    @PostMapping("/{id}/triage")
    public Ticket triage(
            @PathVariable Long id){
        return s.triage(id);}
    @PostMapping("/{id}/draft")
    public Draft draft(
            @PathVariable Long id){
        return s.draft(id);}
    @PostMapping("/{id}/action")
    public ToolAction action(
            @PathVariable Long id,
                             @RequestBody
    Map<String,String> b){
        return s.requestAction(id,b.get("type"),
                b.get("idempotencyKey"));
    }
}
