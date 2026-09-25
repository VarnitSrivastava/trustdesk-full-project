package com.airtribe.trustdesk.ai;
import org.springframework.stereotype.Component;
import java.util.*;
@Component public class MockAiProvider implements AiProvider {
 public TriageResult triage(String m){String x=m.toLowerCase();
  String c=x.contains("refund")||x.contains("return")?"refund":x.contains("warranty")||x.contains("broken")?"warranty":x.contains("ship")||x.contains("delivery")?"shipping":x.contains("bill")||x.contains("charge")?"billing":x.contains("password")||x.contains("account")?"account_security":"general";
  String p=x.contains("fraud")||x.contains("hacked")?"urgent":x.contains("not received")||x.contains("broken")?"high":"medium";
  boolean e=p.equals("urgent")||c.equals("account_security");
  return new TriageResult(c,p,e,"Rule-based demo classification; human review for risky cases.");}
 public DraftResult draft(String m,String ctx)
 {if(ctx==null||ctx.isBlank())
  return new DraftResult("I’m unable to provide a policy-backed answer yet. I’ll escalate this request for review.",
          List.of(),false);
  List<String> ids=new ArrayList<>();
  for(String line:ctx.split("\\n")){if(line.startsWith("DOC:"))
   ids.add(line.substring(4).trim());}
  String body="Thanks for contacting support. Based on our support policy, here is the applicable guidance:\n\n"+ctx.replaceAll("DOC:[^\\n]+\\n?","").trim()+"\n\nIf this does not resolve your issue, we can escalate it to a support specialist.";
  return new DraftResult(body,ids,!ids.isEmpty()); }
}
