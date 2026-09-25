package com.airtribe.trustdesk.controller;
import org.springframework.web.bind.annotation.*;
import com.airtribe.trustdesk.service.TrustDeskService;
import com.airtribe.trustdesk.entity.*;
import java.util.*;
@RestController

@RequestMapping
        ("/api/actions")
public class ActionController {
    private final TrustDeskService s;
    public ActionController(TrustDeskService s){
        this.s=s;} @PostMapping("/{id}/approve")
    public ToolAction approve(@PathVariable Long id,@RequestBody Map<String,Object>b){
        return s.approve(id,String.valueOf(b.getOrDefault("reviewer","demo-agent")),
                Boolean.parseBoolean(String.valueOf(b.getOrDefault("approved",false))));
    }
}
