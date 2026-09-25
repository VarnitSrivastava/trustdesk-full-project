# TrustDesk – AI Support Operations Agent

**Airtribe AI-First Software Engineering Program – Capstone Project**

## 📌 Overview

TrustDesk is an AI-powered customer support operations agent that helps support teams handle repetitive customer queries safely. It performs ticket triage, retrieves relevant support policies, generates grounded response drafts, recommends support actions, requires human approval for sensitive actions, and maintains an audit trail.

## 🎯 Objectives

* Classify customer support tickets by **intent and priority**.
* Identify tickets requiring **human escalation**.
* Retrieve relevant and trusted **knowledge-base policies**.
* Generate **policy-grounded response drafts** with citations.
* Recommend safe actions such as refund review or replacement.
* Require **human approval** before sensitive actions.
* Detect and block **prompt-injection attempts**.
* Maintain an **audit/trace record** of important operations.

## 🛠️ Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Web / REST API**
* **Spring Data JPA / Hibernate**
* **H2 Database**
* **Maven**
* **HTML, CSS & JavaScript**
* **JSON**

## 🏗️ Architecture

```text
Customer
   ↓
Web UI
   ↓
REST Controller
   ↓
TrustDesk Service
   ↓
 ┌────────────┬─────────────┬──────────────┐
 │ Guardrail  │ AI Provider │ Knowledge KB │
 └────────────┴─────────────┴──────────────┘
                    ↓
              Repository / H2
                    ↓
             Audit / Trace
```

## 🔄 Main Workflow

```text
Customer Ticket
      ↓
Guardrail Check
      ↓
AI Triage
      ↓
Knowledge Retrieval
      ↓
Grounded Draft
      ↓
Action Recommendation
      ↓
Human Approval
      ↓
Action Execution
      ↓
Audit Trace
```

## 🔐 Safety Features

**Prompt Injection Protection:**
Suspicious instructions such as *“ignore previous instructions”* are detected and blocked.

**Trusted Knowledge:**
Only trusted support documents are considered during policy retrieval.

**Human-in-the-Loop:**
Sensitive actions are placed in `PENDING_APPROVAL` until a human reviewer approves them.

**Idempotency:**
Idempotency keys help prevent duplicate actions.

**Audit Trail:**
Ticket processing, retrieved documents, AI decisions, actions, and approvals are recorded.

## 📂 Key Components

| Component          | Responsibility                   |
| ------------------ | -------------------------------- |
| `TicketController` | Ticket REST APIs                 |
| `TrustDeskService` | Main business logic              |
| `KnowledgeService` | Policy retrieval                 |
| `GuardrailService` | Security/prompt-injection checks |
| `AiProvider`       | AI abstraction                   |
| `MockAiProvider`   | Demo AI implementation           |
| `ToolAction`       | Sensitive action management      |
| `Approval`         | Human approval                   |
| `Trace`            | Audit trail                      |
| `H2`               | Application database             |

## ▶️ Run the Project

### 1. Check Java

```bash
java -version
```

Java 17 is required.

### 2. Check Maven

```bash
mvn -version
```

### 3. Build

```bash
mvn clean install
```

### 4. Run

```bash
mvn spring-boot:run
```

### 5. Open

```text
http://localhost:8080
```

## 🧪 Example

**Customer:**

```text
I want a refund for my headphones.
```

**System:**

```text
Category: refund
Priority: medium
Escalation: false
```

The system retrieves the refund policy, generates a grounded draft, and can create a refund-review action requiring human approval.

## 📚 Learning Outcomes

This project demonstrates practical understanding of:

* Java & OOP
* Spring Boot
* REST APIs
* Dependency Injection
* JPA/Hibernate
* Database integration
* AI agent architecture
* Knowledge retrieval/RAG concepts
* Guardrails
* Human-in-the-loop workflows
* Idempotency
* Audit logging
* Maven project management

## 🚀 Future Improvements

* Integrate a real LLM.
* Add embedding/vector-based RAG.
* Add PostgreSQL.
* Add JWT authentication and role-based access.
* Integrate real refund/replacement tools.
* Add Docker and cloud deployment.

---

**Project:** TrustDesk – AI Support Operations Agent
**Program:** Airtribe AI-First Software Engineering Program
**Language:** Java 17
**Build Tool:** Maven
