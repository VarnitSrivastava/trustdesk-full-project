# TrustDesk — AI Support Operations Agent

A complete Java 17 + Spring Boot demo implementing the Airtribe TrustDesk capstone. The source brief requires data loading, ticket APIs, a simple frontend, knowledge retrieval with cited drafts, AI triage, one approval-gated action with idempotency, adversarial guardrails, minimal traces, and an evaluation flow. fileciteturn0file0L43-L100

## Architecture
`Browser -> REST Controllers -> TrustDeskService -> {Mock AI Adapter, Guardrails, Knowledge Retrieval} -> JPA/H2`

The AI provider is behind `AiProvider`, so `MockAiProvider` can be replaced by an OpenAI/Anthropic/local adapter without changing the service layer. Retrieval is a deterministic local full-text substitute, satisfying the brief's allowance for a documented local retrieval layer. fileciteturn0file0L105-L130

## Features
- Customers, orders, tickets, KB documents, drafts, actions, approvals and traces persisted in H2.
- Ticket list/get APIs and linked IDs for customer/order context.
- Triage: shipping, refund, warranty, billing, account_security, general; priority low/medium/high/urgent; escalation flag.
- Grounded draft replies with KB document IDs.
- Untrusted customer/KB input handling; unsafe KB document is stored as `trusted=false` and never retrieved.
- Guardrails for prompt injection, hidden-prompt/secret requests, coupon manipulation and identity-check bypass.
- `start_refund_review` and `create_replacement_order` request path; execution requires explicit approval.
- Idempotency key prevents duplicate action records.
- Minimal traces for triage/draft runs.
- `/api/evals/run` evaluation summary endpoint.

## Run
Requirements: JDK 17+, Maven 3.9+.

```bash
mvn spring-boot:run
```
Open `http://localhost:8080/`.

Or package:
```bash
mvn clean package
java -jar target/trustdesk-1.0.0.jar
```

H2 console: `http://localhost:8080/h2-console` with JDBC URL `jdbc:h2:file:./data/trustdesk-db`, user `sa`, blank password.

## API
- `GET /api/tickets`
- `GET /api/tickets/{id}`
- `POST /api/tickets/{id}/triage`
- `POST /api/tickets/{id}/draft`
- `POST /api/tickets/{id}/action` body `{ "type":"start_refund_review", "idempotencyKey":"..." }`
- `POST /api/actions/{id}/approve` body `{ "approved":true, "reviewer":"..." }`
- `GET /api/traces`
- `POST /api/evals/run`

## Evaluation
Cases live in `data/eval_cases.jsonl`. The evaluation endpoint reports total cases, triage matches/accuracy and unsafe-request blocking. For a production implementation, replace the intentionally simple evaluator with strict JSONL parsing and exact expected-vs-actual assertions for citation coverage, escalation, and tool safety.

## Demo flow
1. Open the UI.
2. Triage the refund ticket.
3. Generate a policy-grounded draft and inspect citation IDs.
4. Request refund review; observe `PENDING_APPROVAL`.
5. Approve it as the human reviewer; observe `EXECUTED`.
6. Try the adversarial ticket; the guardrail blocks it and escalates.
7. Run `POST /api/evals/run`.

## Design decisions
- H2 file database keeps the project runnable with zero external infrastructure.
- Rule-based mock AI makes tests/demo deterministic and keeps provider-specific code isolated.
- Trusted KB filtering treats retrieved documents as data, not instructions. This directly addresses the brief's requirement that `KB-ADVERSARIAL-001` must not be followed. fileciteturn0file0L85-L93
- Sensitive actions are two-phase: recommendation/request first, approval second.
- Idempotency is checked before creating an action.

## Known limitations
- Demo authentication is not yet implemented; add a token filter or Spring Security for deployment.
- Retrieval is lexical rather than embedding-based.
- No async job queue yet; production deployments should run long AI/evaluation jobs asynchronously as requested by the brief. fileciteturn0file0L120-L128
- Demo evaluator is intentionally lightweight.
- Customer/order data are linked by IDs; a production UI should display only authorized context and redact unnecessary PII.

## Deliverables checklist
The brief asks for a functional product, README, GitHub repository, explainer video and evaluation summary. fileciteturn0file0L149-L165 This package supplies the runnable product, documentation, seed data, UI and evaluation endpoint; GitHub publication and video recording must be performed from your account.
