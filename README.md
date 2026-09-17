# 📅 Appointment Management Platform

A scalable, microservices-based appointment management platform. Customers can browse businesses, book appointments, make payments, and receive real-time notifications.

---

# 🚀 Tech Stack

## Backend (Microservices)

- Java + Spring Boot
- Spring Cloud (Eureka, Gateway)
- RabbitMQ (Event-driven communication)
- REST APIs (Feign Clients)
- Keycloak (Authentication & Authorization)
- Maven

## Frontend

- React.js
- Redux (State Management)
- Tailwind CSS

## DevOps & Tools

- Docker & Docker Compose
- Git

---

# 🧩 Microservices Overview

| Service                  | Description                                                   |
| ------------------------ | ------------------------------------------------------------- |
| **User Service**         | Handles authentication, user management, Keycloak integration |
| **Business Service**        | Manages business profiles and details                            |
| **Booking Service**      | Handles appointment booking logic                             |
| **Category Service**     | Manages service categories                                    |
| **Service Offering**     | Manages services offered by businesses                            |
| **Payment Service**      | Handles payments and order status                             |
| **Review Service**       | Manages reviews and ratings                                   |
| **Notification Service** | Real-time notifications via WebSocket                         |
| **Gateway Server**       | API Gateway (routing, security)                               |
| **Eureka Server**        | Service discovery                                             |

---

# 🏗️ System Architecture

- Microservices architecture
- API Gateway for routing
- Service discovery using Eureka
- Event-driven communication using RabbitMQ
- Frontend communicates via Gateway

---

# 📁 Project Structure

```
backend/
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
  └── docker-compose/

frontend/
  ├── src/
  ├── public/
```

---

# ⚙️ Setup Instructions

## 1. Clone Repository

```
git clone <your-repo-url>
cd Full-Stack-Business-Booking
```

## 2. Start Infrastructure (Docker)

```
cd backend/docker-compose/default
docker-compose up -d
```

## 3. Run Backend Services

Run services in this order:

1. Eureka Server
2. Gateway Server
3. User Service
4. Other services (booking, business, etc.)

Each service:

```
cd <service-folder>
mvn spring-boot:run
```

## 4. Run Frontend

```
cd frontend
npm install
npm start
```

Frontend will run at: [http://localhost:3000](http://localhost:3000)

---

# 🔐 Authentication Flow

- Uses Keycloak for authentication
- JWT tokens are passed through Gateway
- Gateway validates and forwards requests

---

# 🔄 Event-Driven Communication

- RabbitMQ used for async communication
- Example:
  - Payment → Booking update
  - Booking → Notification trigger

---

# 💡 Features

### 👤 Customer

- Register & Login
- Browse businesses
- View services
- Book appointments
- Make payments
- Receive notifications
- Write reviews

### 🏢 Business Owner

- Manage business profile
- Add/update services
- View bookings
- Track earnings

### 🛠 Admin

- Dashboard analytics
- Manage businesses

---

# 📊 Key Functionalities

- Real-time notifications (WebSocket)
- Role-based access control
- Microservices communication via Feign
- API Gateway routing
- Booking analytics

---

# 📦 API Flow Example

1. User books service
2. Booking service processes request
3. Payment service creates payment order
4. Payment success triggers event
5. Notification service sends update

---

# 🧪 Testing

Each service includes basic test setup:

```
mvn test
```

---

# 🛠 Future Improvements

- Add CI/CD pipeline
- Kubernetes deployment
- Advanced analytics dashboard
- AI-based recommendations
- Multi-language support

---

# 🤝 Contributing

1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push and create PR

---

# 📄 License

This project is licensed under the MIT License.

---

# 🙌 Acknowledgements

- Spring Boot
- React
- RabbitMQ
- Keycloak
