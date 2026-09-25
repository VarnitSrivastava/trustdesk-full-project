package com.airtribe.trustdesk.ai;
import java.util.*;
public interface AiProvider { TriageResult triage(String message);
    DraftResult draft(String message,String context);
    record TriageResult(String category,String priority,
                        boolean escalate,String reason){}
    record DraftResult(String body,List<String> citations,
                       boolean grounded){} }
