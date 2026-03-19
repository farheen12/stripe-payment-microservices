
---

## 🧩 Services

### 🔹 Payment Processing Service (PPS)
- Manages transaction lifecycle
- Handles business logic and DB updates
- Maintains transaction states

### 🔹 Stripe Provider Service (SPS)
- Communicates with Stripe APIs
- Creates payment sessions
- Returns provider details to PPS

---

## 🔄 Payment Flow

### 1. Create Transaction
- Generate `txnReferenceId`
- Status → `CREATED`

### 2. Initiate Payment
- Fetch transaction
- Status → `INITIATED`
- Call SPS → Stripe API
- Store `providerId`, `checkoutUrl`
- Status → `PENDING`

### 3. Webhook Processing
- Stripe sends payment events
- Signature verified using HMAC SHA256
- Events processed asynchronously
- Transaction status updated in DB

---

## ⚙️ Key Features

- ✔ Factory Design Pattern for status management  
- ✔ Async webhook processing (prevents duplicate retries)  
- ✔ Secure Stripe signature verification  
- ✔ Microservice-based architecture  
- ✔ AI-based error summarization for cleaner API responses  

---

## ⚠️ Failure Handling

| Scenario                     | Behavior              |
|----------------------------|----------------------|
| Invalid signature           | Stripe retries       |
| Request validation failure  | 4xx response         |
| Downstream/service failure  | 5xx response         |
| Async processing failure    | Logged (no retry)    |

---

## 🧠 Design Decisions

- **Async Webhooks** → Avoid Stripe retries & duplicate DB updates  
- **Factory Pattern** → Clean status transition handling  
- **Service Separation** → Decouples business logic from external APIs  

---

## 🚀 Future Improvements

- Retry mechanism (Kafka / Queue)
- Dead Letter Queue (DLQ)
- Idempotency handling for webhooks
- Monitoring & alerting

---

## 🧪 Tech Stack

- Java  
- Spring Boot  
- REST Client  
- Stripe API  
- Async Processing  

---

## ▶️ How to Run

```bash
# Clone the repository
git clone https://github.com/farheen12/stripe-payment-microservices.git

# Navigate to project
cd stripe-payment-microservices

# Run services (example)
mvn spring-boot:run
