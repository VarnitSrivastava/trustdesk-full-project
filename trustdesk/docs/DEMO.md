# 5-minute demo script

1. Start the app and open `/`.
2. Show the seeded refund ticket and click **Triage**. Explain category, priority and escalation.
3. Click **Generate Draft**. Point out that the response is generated from trusted KB documents and returns citation IDs such as `KB-REFUND-001`.
4. Click **Request Refund Review**. The action is stored as `PENDING_APPROVAL`; the UI then asks for explicit human approval.
5. Approve it and show `EXECUTED`. Repeat the same idempotency key to demonstrate no duplicate action.
6. Open the adversarial ticket and click **Triage**. Explain that prompt-injection text is untrusted input and is blocked/escalated.
7. Call `POST /api/evals/run` with header `X-Demo-Token: demo-token` and show the evaluation JSON.
