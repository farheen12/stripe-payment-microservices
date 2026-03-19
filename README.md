# 💳 Stripe Payment Microservices

A microservice-based system for secure payment processing using Stripe.

---

## 🏗️ Services

### Payment Processing Service (PPS)
- Manages transaction lifecycle
- Handles business logic & DB updates

### Stripe Provider Service (SPS)
- Communicates with Stripe APIs
- Returns session details to PPS

---

## 🔄 Payment Flow

### 1. Create Transaction
- Generate `txnReferenceId`
- Status → `CREATED`

### 2. Initiate Payment
- Fetch transaction
- Status → `INITIATED`
- Call SPS → Stripe
- Store `providerId`, `checkoutUrl`
- Status → `PENDING`

### 3. Webhook Processing
- Stripe sends events
- Signature verified (HMAC SHA256)
- Processed asynchronously
- DB updated with latest status

---

## ⚙️ Key Features

- Factory Pattern for status handling  
- Async webhook processing (prevents duplicate retries)  
- Stripe signature verification  
- Clear separation of services  
- AI-based error summarization  

---

## ⚠️ Failure Handling

| Scenario           | Result              |
|------------------|--------------------|
| Invalid signature | Stripe retries     |
| Sync failure      | Error returned     |
| Async failure     | Logged (no retry)  |

---

## 🚀 Improvements

- Retry mechanism for async failures  
- Idempotency handling  
- Monitoring & alerts  

---

## 🧪 Tech Stack

Java • Spring Boot • Stripe API • REST Client • Async Processing
