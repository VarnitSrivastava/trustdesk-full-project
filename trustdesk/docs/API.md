# API quick reference

See README for all endpoints. All mutation endpoints use POST and return JSON.

### Create approval-gated action
```json
POST /api/tickets/1/action
{"type":"start_refund_review","idempotencyKey":"refund-ticket-1-v1"}
```

### Approve
```json
POST /api/actions/1/approve
{"approved":true,"reviewer":"human-reviewer"}
```
