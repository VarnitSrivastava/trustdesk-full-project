package com.airtribe.trustdesk.service;
import org.springframework.stereotype.Service;
import com.airtribe.trustdesk.entity.*;
import com.airtribe.trustdesk.repository.*;
import com.airtribe.trustdesk.ai.*;
import com.airtribe.trustdesk.guardrail.*;
import java.time.*;
import java.util.*;

@Service
public class TrustDeskService {

 private final TicketRepository tickets;
 private final CustomerRepository customers;
 private final OrderRepository orders;
 private final KnowledgeService kb;
 private final AiProvider ai;
 private final GuardrailService guard;
 private final DraftRepository drafts;
 private final ToolActionRepository actions;
 private final ApprovalRepository approvals;
 private final TraceRepository traces;

 public TrustDeskService(TicketRepository t,CustomerRepository c,OrderRepository o,KnowledgeService k,AiProvider a,GuardrailService g,DraftRepository d,ToolActionRepository ac,ApprovalRepository ap,TraceRepository tr){
  tickets=t;customers=c;
  orders=o;
  kb=k;
  ai=a;
  guard=g;
  drafts=d;
  actions=ac;
  approvals=ap;
  traces=tr;
 }
 public List<Ticket> list(){
  return tickets.findAll();
 }
 public Ticket get(Long id){
  return tickets.findById(id).orElseThrow();
 }
 public Ticket triage(Long id){
  Ticket t=get(id);
  var gr=guard.check(t.message);
  if(!gr.allowed()){
   t.escalated=true;
   t.status="ESCALATED";
   tickets.save(t);
   trace(t,"TRIAGE",gr.code(),"BLOCKED");
   return t;}
  var r=ai.triage(t.message);
  t.category=r.category();
  t.priority=r.priority();
  t.escalated=r.escalate();
  tickets.save(t);
  trace(t,"TRIAGE","", "COMPLETED");
  return t;
 }
 public Draft draft(Long id){Ticket t=get(id);
  var gr=guard.check(t.message);
  if(!gr.allowed()){trace(t,"DRAFT",gr.code(),"BLOCKED");
   throw new IllegalStateException("Request blocked by guardrail");
  }
  var docs=kb.search(t.message);
  StringBuilder ctx=new StringBuilder();
  for(var d:docs)ctx.append("DOC:").append(d.docId).append("\n").append(d.title).append("\n").append(d.content).append("\n");
  var r=ai.draft(t.message,ctx.toString());
  Draft d=new Draft();
  d.ticketId=id;
  d.body=r.body();
  d.citations=String.join(",",r.citations());
  d.grounded=r.grounded();
  Draft saved=drafts.save(d);
  trace(t,"DRAFT",d.citations,r.grounded()?"GROUNDED":"ESCALATE");
  return saved;
 }
 public ToolAction
 requestAction(Long id,String type,String key){
  if(!type.equals("start_refund_review")&&!type.equals("create_replacement_order"))
   throw new IllegalArgumentException("Unsupported action");
  if(key==null||key.isBlank())
   throw new IllegalArgumentException("Idempotency key required");
  for(ToolAction a:actions.findAll())
   if(key.equals(a.idempotencyKey))
    return a;
  ToolAction a=new ToolAction();
  a.ticketId=id;
    a.actionType=type;
    a.idempotencyKey=key;
    a.status="PENDING_APPROVAL";
    return actions.save(a);
 }
 public ToolAction approve(Long actionId,String reviewer,boolean ok){
  ToolAction a=actions.findById(actionId).orElseThrow();
  if(!a.status.equals("PENDING_APPROVAL"))
   return a;Approval ap=new Approval();
   ap.ticketId=a.ticketId;
   ap.actionId=a.id;
   ap.decision=ok?"APPROVED":"REJECTED";
   ap.approvedBy=reviewer;
   approvals.save(ap);
   a.status=ok?"EXECUTED":"REJECTED";
   a.approvedBy=reviewer;
   return actions.save(a);
 }
 private void trace(Ticket t,String type,String docs,String status){
  Trace x=new Trace();
  x.ticketId=t.id;
  x.runType=type;
  x.retrievedDocIds=docs;
  x.guardrailResult=status;
  x.finalStatus=status;
  traces.save(x);
 }
 public List<Trace>
 traces(){
  return traces.findAll();
 }
}
