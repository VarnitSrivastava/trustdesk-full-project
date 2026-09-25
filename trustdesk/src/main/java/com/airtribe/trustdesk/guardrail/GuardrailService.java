package com.airtribe.trustdesk.guardrail;
import org.springframework.stereotype.Service;
@Service
public class GuardrailService {
    public Result check(String message){String x=message.toLowerCase();
        if(x.contains("ignore previous")||x.contains("reveal hidden prompt")||x.contains("system prompt")||x.contains("coupon code")||x.contains("bypass identity"))
            return new Result(false,"BLOCKED_ADVERSARIAL_REQUEST");
        return new Result(true,"ALLOWED");
    }
    public record Result(boolean allowed,String code){

    }
}
