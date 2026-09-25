package com.airtribe.trustdesk.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.nio.file.*;
import java.util.*;
import com.airtribe.trustdesk.ai.*;
import com.airtribe.trustdesk.guardrail.*;
@RestController
@RequestMapping
        ("/api/evals")
public class EvalController {
    private final AiProvider ai;
    private final GuardrailService g;
    public EvalController(AiProvider a,GuardrailService g){
        ai=a;this.g=g;} @PostMapping("/run")
    public Map<String,Object> run(){
        try{var lines=Files.readAllLines(Path.of("data/eval_cases.jsonl"));
            int total=0,tri=0,blocked=0;
            for(String l:lines){
                if(l.isBlank())continue;total++;
                if(l.contains("expectedCategory")&&l.contains("refund")&&ai.triage(l).category().equals("refund"))tri++;
                if(!g.check(l).allowed())blocked++;}
            return Map.of("total",total,"triageMatches",tri,"triageAccuracy",total==0?0.0:(double)tri/total,
                    "unsafeRequestsBlocked",blocked,
                    "note","Demo evaluator; replace with full JSON parsing for expanded cases.");}
        catch(Exception e){
            return Map.of("error",e.getMessage());
        }
    }
}
