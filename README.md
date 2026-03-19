# 💳 Stripe Payment Microservices

A microservice-based system for handling secure payment processing using Stripe.

---

## 🏗️ Services
### 🔹 Payment Processing Service (PPS)
- Manages transaction lifecycle
- Handles business logic
- Stores transaction details in DB

### 🔹 Stripe Provider Service (SPS)
- Communicates with Stripe APIs
- Returns payment session details to PPS

---

## 🔄 Payment Flow

### 1️⃣ Create Transaction
- Generate `txnReferenceId`
- Status → `CREATED`

### 2️⃣ Initiate Payment
- Fetch transaction using `txnReferenceId`
- Status → `INITIATED`
- Call SPS → Stripe API
- Store `providerId`, `checkoutUrl`
- Status → `PENDING`

### 3️⃣ Webhook Processing
- Stripe sends payment events
- Signature verified (HMAC SHA256)
- Events processed asynchronously
- Status updated in DB

---

## ⚙️ Key Highlights

- ✔ Factory Design Pattern for status updates  
- ✔ Async webhook handling (avoids duplicate retries)  
- ✔ Secure Stripe signature verification  
- ✔ Clear separation of concerns (PPS & SPS)  
- ✔ AI-based error summarization  

---

## ⚠️ Failure Handling

| Scenario           | Behavior              |
|------------------|----------------------|
| Invalid signature | Stripe retries       |
| Sync failure      | Error returned       |
| Async failure     | Logged (no retry)    |

---

## 🚀 Future Improvements

- Retry mechanism for async failures  
- Dead Letter Queue (DLQ)  
- Idempotency handling  
- Monitoring & alerting  

---

## 🧪 Tech Stack

`Java` • `Spring Boot` • `Stripe API` • `REST Client` • `Async Processing`
