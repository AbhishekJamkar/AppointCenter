# ⚙️ Business Booking Backend API (Microservices)

A **scalable, production-ready backend system** for a business booking platform built using **Spring Boot microservices architecture**.

This backend powers features like booking, payments, authentication, notifications, and service management using **event-driven architecture and service-to-service communication**.

---

# 🚀 Tech Stack

## 🧠 Core Technologies

- Java
- Spring Boot
- Spring Cloud (Eureka, API Gateway)
- Spring Security

## 🔐 Authentication & Security

- Keycloak (OAuth2 + Role-based access control)

## 🔄 Communication

- OpenFeign (Synchronous communication)
- RabbitMQ (Asynchronous messaging)

## 💾 Database

- MySQL
- Spring Data JPA (Hibernate)

## ⚙️ Build Tool

- Maven

## 🐳 DevOps

- Docker & Docker Compose

---

# 🧩 Microservices Overview

| Service                  | Description                                           |
| ------------------------ | ----------------------------------------------------- |
| **User Service**         | Authentication, user management, Keycloak integration |
| **Business Service**        | Manage businesses, profiles, and details                  |
| **Booking Service**      | Appointment booking and scheduling                    |
| **Category Service**     | Manage business categories                               |
| **Service Offering**     | Manage services provided by businesses                    |
| **Payment Service**      | Payment processing (Stripe/Razorpay)                  |
| **Review Service**       | Ratings and reviews                                   |
| **Notification Service** | Real-time notifications (WebSocket + RabbitMQ)        |
| **Gateway Server**       | API Gateway for routing and security                  |
| **Eureka Server**        | Service discovery                                     |

---

# 🏗️ Architecture

- Microservices architecture
- API Gateway pattern
- Service discovery via Eureka
- Event-driven communication using RabbitMQ
- REST APIs for inter-service communication
- Centralized authentication with Keycloak

---

# 📁 Project Structure

```bash
backend (microservices)/
├── booking/
├── category/
├── business/
├── service-offering/
├── payment/
├── review/
├── notifications/
├── user-service/
├── gateway-server/
├── eurekaserver/
├── docker-compose/
```

---

# 🔄 System Flow

1. Client sends request via API Gateway
2. Gateway authenticates request (Keycloak)
3. Request routed to appropriate microservice
4. Services communicate via:
   - OpenFeign (sync)
   - RabbitMQ (async events)

5. Response returned to client

---

# 📡 Event-Driven Communication

RabbitMQ is used for:

- Booking → Payment updates
- Payment → Notification events
- Booking → Notification triggers

---

# 🔗 Inter-Service Communication

- Feign Clients used across services:
  - User Service
  - Business Service
  - Payment Service
  - Category Service

---

# 🔐 Security

- Keycloak-based authentication
- JWT token validation
- Role-based access control:
  - Admin
  - Customer
  - Business Owner

---

# ⚙️ Setup Instructions

## 1️⃣ Clone the Repository

```bash
git clone https://github.com/<your-username>/Full-Stack-Business-Booking-Web-App.git
cd backend
```

---

## 2️⃣ Start Infrastructure (Docker)

```bash
cd docker-compose/default
docker-compose up -d
```

---

## 3️⃣ Configure Environment Variables

Create `.env` file:

```env
DB_PASSWORD=your_db_password
MAIL_USERNAME=your_email
MAIL_PASSWORD=your_email_password
RAZORPAY_KEY=your_key
RAZORPAY_SECRET=your_secret
STRIPE_SECRET_KEY=your_stripe_key
```

---

## 4️⃣ Run Services

Start services in order:

1. Eureka Server
2. Gateway Server
3. User Service
4. Other microservices

```bash
cd <service-name>
mvn spring-boot:run
```

---

# 📌 Key Features

- Microservices architecture
- Service discovery (Eureka)
- API Gateway routing
- Secure authentication (Keycloak)
- Event-driven messaging (RabbitMQ)
- Payment integration
- Real-time notifications (WebSocket)
- Scalable and modular design

---

# 📊 API Endpoints (Overview)

Each microservice exposes REST APIs:

### User Service

- `/auth/login`
- `/auth/register`
- `/users`

### Booking Service

- `/bookings`
- `/slots`
- `/history`

### Business Service

- `/businesses`
- `/services`

### Payment Service

- `/payments`
- `/orders`

### Notification Service

- `/notifications`

---

# 🧪 Testing

```bash
mvn test
```

---

# 🚀 Future Improvements

- CI/CD pipeline (GitHub Actions)
- Kubernetes deployment
- API rate limiting
- Distributed tracing (Zipkin)
- Monitoring (Prometheus + Grafana)

---

# 🤝 Contributing

1. Fork the repo
2. Create feature branch
3. Commit changes
4. Open Pull Request

---

# 📄 License

MIT License

---

# 🙌 Acknowledgements

- Spring Boot
- RabbitMQ
- Keycloak
- MySQL

---

# 📬 Contact

For queries or collaboration, feel free to connect.
