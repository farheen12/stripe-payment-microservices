💳 Payment Processing System

A microservice-based payment system integrating Stripe for secure transaction processing.

🏗️ Services

Payment Processing Service (PPS)
Handles transactions, business logic, and database updates

Stripe Provider Service (SPS)
Communicates with Stripe APIs

🔄 Flow
1️⃣ Create Transaction
Generates txnReferenceId
Sets status → CREATED
2️⃣ Initiate Payment
Updates status → INITIATED
Calls SPS → Stripe API
Stores providerId, checkoutUrl
Updates status → PENDING
3️⃣ Webhook Handling
Stripe sends events
Signature verified using HMAC SHA256
Events processed asynchronously
Transaction status updated in DB

⚙️ Key Features

Factory Design Pattern for status handling
Async webhook processing (prevents duplicate retries)
Secure Stripe signature verification
Clean separation of services (PPS & SPS)
AI-based error summarization

⚠️ Failure Handling
Scenario	         Behavior
Invalid signature	 Stripe retries
Sync failure	     Proper error response
Async failure	     Logged (no retry)

🚀 Improvements

Retry mechanism for async failures
Idempotency handling



🧪 Tech Stack

Java, Spring Boot, REST Client, Stripe API, Async Processing
