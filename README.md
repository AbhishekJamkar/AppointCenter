# 📅 AppointCenter: Full-Stack Appointment Booking Platform

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React.js-18.x-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)](https://react.dev/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Keycloak](https://img.shields.io/badge/Keycloak-26.x-blue?style=for-the-badge&logo=redhat&logoColor=white)](https://www.keycloak.org/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

**AppointCenter** is an enterprise-grade, distributed multi-service appointment scheduling and booking platform. Designed for multi-domain service industries (Clinics, Salons, Legal Advisory, Photo Studios, Astrology, etc.), it connects service providers (Business Owners) with customers through real-time calendar slot management, Razorpay payment processing, and centralized Keycloak Identity & Access Management (IAM).

---

## 📑 Table of Contents
1. [Key Features](#-key-features)
2. [Architecture Overview](#-architecture-overview)
3. [Tech Stack](#️-tech-stack)
4. [Project Structure](#-project-structure)
5. [Prerequisites](#-prerequisites)
6. [Step-by-Step Setup Guide & Execution Walkthrough](#-step-by-step-setup-guide--execution-walkthrough)
   - [Step 1: MySQL Database Initialization](#step-1-mysql-database-initialization)
   - [Step 2: Keycloak IAM Setup (Docker)](#step-2-keycloak-iam-setup-docker)
   - [Step 3: Backend Microservices Setup](#step-3-backend-microservices-setup)
   - [Step 4: Frontend Setup (React.js)](#step-4-frontend-setup-reactjs)
7. [Visual Application Walkthrough & User Flows](#-visual-application-walkthrough--user-flows)
   - [1. User Authentication & Registration](#1-user-authentication--registration)
   - [2. Partner / Business Owner Onboarding](#2-partner--business-owner-onboarding)
   - [3. Business Owner Dashboard & Service Setup](#3-business-owner-dashboard--service-setup)
   - [4. Customer Discovery & Slot Booking](#4-customer-discovery--slot-booking)
   - [5. Razorpay Checkout & Order Confirmation](#5-razorpay-checkout--order-confirmation)
   - [6. Customer My Bookings & Reviews](#6-customer-my-bookings--reviews)
8. [Service Port Registry](#-service-port-registry)
9. [Security & Role-Based Access Control](#-security--role-based-access-control)
10. [License & Author](#-license--author)

---

## ✨ Key Features

### 👤 Customer Features
- **Multi-Category Browsing**: Explore clinics/hospitals, beauty parlours, photography studios, legal advisors, architects, and consultancies.
- **Smart Slot Booking**: Dynamic date & time picker showing available and reserved appointment slots.
- **Razorpay Payment Gateway**: Seamless checkout with UPI, Credit/Debit Cards, Net Banking, and QR code payments.
- **My Bookings Dashboard**: View upcoming appointments, track booking statuses (`PENDING`, `CONFIRMED`, `CANCELLED`), and cancel bookings.
- **Ratings & Reviews**: Write detailed reviews and rate services with interactive star ratings.

### 🏢 Partner / Business Owner Features
- **Partner Onboarding Wizard**: Multi-step registration (Owner Details ➔ Business Details ➔ Business Address & Operating Hours).
- **Vendor Dashboard**: Real-time revenue analytics charts, total bookings, total refunds, and cancellation counters.
- **Category & Service Management**: Add/edit custom services, upload pictures, define durations (minutes), and set pricing.
- **Booking Management**: Accept, monitor, and cancel customer bookings.
- **Financial Records**: Comprehensive transaction tables tracking customer details, booking IDs, and amounts received.

---

## 🏛️ Architecture Overview

The system is designed on **Cloud-Native Microservices Architecture**:
- **API Gateway (`gateway-server`)**: Single entry point for routing, rate limiting, and security filters.
- **Service Discovery (`eurekaserver`)**: Dynamically manages the lifecycle and IP registration of all microservices.
- **Keycloak IAM**: Centralized token issuer (OpenID Connect / OAuth2) enforcing JSON Web Tokens (JWT) for authentication and role checking.
- **Relational Stores**: Isolated MySQL database instances per domain service.

---

## 🛠️ Tech Stack

| Domain | Technology / Library |
| :--- | :--- |
| **Frontend** | React.js, Redux Toolkit, Tailwind CSS, Axios, React Router DOM |
| **Backend** | Java 17+, Spring Boot 3.x, Spring Cloud Netflix Eureka, Spring Cloud Gateway, Spring Data JPA |
| **Authentication** | Keycloak 26.x (Running in Docker), OAuth2 Resource Server, JWT |
| **Database** | MySQL 8.x Server & MySQL Workbench |
| **Payment Gateway**| Razorpay API Integration |
| **DevOps & Tools** | Docker Desktop, Maven, IntelliJ IDEA, VS Code |

---

## 📂 Project Structure

```text
AppointCenter/
├── backend (microservices)/
│   ├── eurekaserver/           # Netflix Eureka Service Discovery (Port: 8761)
│   ├── gateway-server/         # Spring Cloud Gateway (Port: 8080 / 8222)
│   ├── user-service/           # User authentication & profile management
│   ├── business/               # Vendor profiles, business hours & addresses
│   ├── service-offering/       # Service catalog & pricing items
│   ├── category/               # Taxonomy & service categorization
│   ├── booking/                # Appointment scheduling & slot validation
│   ├── payment/                # Razorpay transactions & refunds
│   ├── review/                 # Ratings, review submission & moderation
│   ├── notifications/          # Email and SMS alert dispatchers
│   └── docker-compose/         # Docker Compose scripts for containerized infra
│
└── frontend/
    ├── public/                 # Static assets & index.html
    ├── src/
    │   ├── Admin/              # Platform admin UI
    │   ├── admin seller/       # Business dashboard, service & booking management
    │   ├── Auth/               # Keycloak integration, login guards & session state
    │   ├── config/             # Axios base URL & API route mappings
    │   ├── Customer/           # Home, search, booking modal, & customer views
    │   ├── Data/               # Static mock definitions & category data
    │   ├── Redux/              # Global application state management
    │   ├── routes/             # App route configuration
    │   ├── salon/              # Business detail pages & reviews
    │   ├── Theme/              # Global Tailwind theme styling
    │   └── util/               # Utility functions & token interceptors
    ├── package.json
    └── tailwind.config.js

```

---

## ⚙️ Prerequisites

Ensure you have the following installed on your machine:

* **Git** (`git --version`)
* **Java Development Kit (JDK 17 or higher)** (`java -version`)
* **Apache Maven 3.8+** (`mvn -v`)
* **Node.js (v18.x+) & npm** (`node -v`, `npm -v`)
* **MySQL Server 8.0+** & **MySQL Workbench**
* **Docker Desktop** (Make sure Docker Daemon is running)

---

## 🚀 Step-by-Step Setup Guide & Execution Walkthrough

Follow these steps in sequence to run the entire project on your local machine.

### Step 1: MySQL Database Initialization

Open **MySQL Workbench** or MySQL CLI and run the following script to create all dedicated databases for each microservice:

```sql
CREATE DATABASE IF NOT EXISTS appointment_bookingdb;
CREATE DATABASE IF NOT EXISTS appointment_categorydb;
CREATE DATABASE IF NOT EXISTS appointment_notificationdb;
CREATE DATABASE IF NOT EXISTS appointment_paymentdb;
CREATE DATABASE IF NOT EXISTS appointment_reviewdb;
CREATE DATABASE IF NOT EXISTS appointment_servicedb;
CREATE DATABASE IF NOT EXISTS appointment_userdb;
CREATE DATABASE IF NOT EXISTS appointmentdb;
CREATE DATABASE IF NOT EXISTS keycloak;

```

#### 📸 MySQL Database Schema & Workbench Setup

> *MySQL Workbench screenshot:*
> <img width="1920" height="1080" alt="MySQL_DB" src="https://github.com/user-attachments/assets/57f6b486-d345-4d49-aab9-c8b5b5471ec6" />
<img width="1920" height="1080" alt="MySQL_DB_Tables" src="https://github.com/user-attachments/assets/717c2d9e-70be-4504-98dc-d65d3d2c324c" />



> **Database Configuration:** Verify that your `application.properties` or `application.yml` file in each backend service has matching credentials:
> ```properties
> spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>?createDatabaseIfNotExist=true
> spring.datasource.username=root
> spring.datasource.password=your_mysql_password
> spring.jpa.hibernate.ddl-auto=update
> 
> ```
> 
> 

---

### Step 2: Keycloak IAM Setup (Docker)

Keycloak runs inside a Docker container to provide central identity and access management.

#### 1. Run Keycloak Container

Execute the following command in PowerShell or Terminal:

```powershell
docker run -d --name keycloak2 -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev

```

#### 📸 Docker CLI & Docker Desktop Running Keycloak

> *Terminal command and Docker Desktop container running screenshots:*
> <img width="1920" height="1080" alt="3 Keyclock_Create_Page" src="https://github.com/user-attachments/assets/2ccabb6a-e262-41a6-bb4c-28732bf911c6" />
<img width="1920" height="1080" alt="4 Keyclock_Docker_Page" src="https://github.com/user-attachments/assets/c4d79a52-9981-4f9c-b0ac-d1caae4c105d" />
<img width="1920" height="1080" alt="5 Keyclock_Docker_Page2" src="https://github.com/user-attachments/assets/b40e1d15-fe1f-4223-a5bf-e4bc71f12ae9" />





#### 2. Keycloak Console Configuration

1. Open your browser and navigate to: `http://localhost:8081`
2. Log into the **Keycloak Administration Console** with credentials:
* **Username:** `admin`
* **Password:** `admin`
* <img width="1920" height="1080" alt="6 Keyclock_Login_Page" src="https://github.com/user-attachments/assets/fe776d6d-f04a-47a4-8c17-86516866fe86" />
<img width="1920" height="1080" alt="8 Keyclock_Client_Page" src="https://github.com/user-attachments/assets/12428260-098c-459e-9cb0-09b1e7287e2a" />





#### 3. Client Creation (`business-booking-client`)

* Navigate to **Clients** > click **Create client**.
* **Client type:** `OpenID Connect`
* **Client ID:** `business-booking-client`
* Under **Capability config**:
* Turn **Client authentication** to `On`.
* Under **Authentication flow**, check:
* [x] `Standard flow`
* [x] `Direct access grants`




* Under **Access settings**:
* **Valid Redirect URIs:** `http://localhost:3000/*`
* **Web Origins:** `http://localhost:3000` (or `*`)


* Click **Save**.

#### 📸 Keycloak Client Creation Screens

> *Keycloak Client setup screenshots:*
> <img width="1920" height="1080" alt="8 Keyclock_Client_Page" src="https://github.com/user-attachments/assets/78899d47-f63d-4443-b26a-94e3347a1f70" />
<img width="1920" height="1080" alt="9 Keyclock_Create_Client_Page1" src="https://github.com/user-attachments/assets/fb980186-7862-4281-b3aa-2e36560cd822" />
<img width="1920" height="1204" alt="10 Keyclock_Create_Client_Page2" src="https://github.com/user-attachments/assets/4d65e890-e85c-4e68-9fb7-9ee9989db879" />
<img width="1920" height="1080" alt="11 1 Keyclock_Create_Client_Page3" src="https://github.com/user-attachments/assets/6f3b524e-e97f-47f2-838b-439a77058145" />
<img width="1920" height="2972" alt="11 Keyclock_Create_Client_Page4 png" src="https://github.com/user-attachments/assets/9daed103-7c0f-48d9-baf2-b991189945aa" />
<img width="1920" height="1094" alt="12 1 Keyclock_Client_Created_Page5" src="https://github.com/user-attachments/assets/e16d9e51-4fef-4e6f-94a7-c953c0481f5c" />


#### 4. Configure Roles & Users in Keycloak

* Navigate to **Clients** > Select `business-booking-client` > **Roles** > Click **Create role**:
* Create role: `CUSTOMER`
* Create role: `SALON OWNER`
* Create role: `ADMIN`
<img width="1920" height="869" alt="12 Client_Create role_Page1" src="https://github.com/user-attachments/assets/e812d892-747f-48e0-a6e6-761250819e4b" />
<img width="1920" height="869" alt="13 Client_Create role_Page2" src="https://github.com/user-attachments/assets/bf342036-ed80-43f6-bce0-a93ffec24cef" />
<img width="1920" height="869" alt="14 Client_role_Created_Page3" src="https://github.com/user-attachments/assets/6c214946-c6e9-4bab-9ab7-1c2a0a26aaaf" />
<img width="1920" height="1080" alt="15 Client_Id_And_Secret_key" src="https://github.com/user-attachments/assets/1b390afa-8e36-4685-8a41-4c409dc251eb" />


* Navigate to **Users** > Click **Add user**:
* **Username:** `admin_abhi`
* **Email:** `admin@gmail.com`

<img width="1920" height="1080" alt="16 Users_page" src="https://github.com/user-attachments/assets/8f753ce0-c16e-471c-8eed-1e112bc16762" />
<img width="1920" height="1065" alt="17 Create_user_Page" src="https://github.com/user-attachments/assets/0972e66a-cfb6-4782-8083-39d5905b3038" />

* Go to the **Credentials** tab > Click **Set password** (`admin`), toggle `Temporary` to `Off`.
* Go to the **Role mapping** tab > Assign the `admin` or client roles.
<img width="1920" height="1080" alt="18 Assign_Role_page" src="https://github.com/user-attachments/assets/fa476a9f-bec3-4e0b-8751-992eb93ed60b" />
<img width="1920" height="1080" alt="19 Assign_Role_page2" src="https://github.com/user-attachments/assets/5e03a8cf-3fd9-40d9-951c-6e7eb96a1059" />
<img width="1920" height="1080" alt="20 Assign_Role_page3" src="https://github.com/user-attachments/assets/c18415cd-5c16-4e97-abfa-240957836bd8" />
<img width="1920" height="1080" alt="21 Assign_Role_page4" src="https://github.com/user-attachments/assets/483fbafe-8c5c-478b-8c68-819de2a87c81" />
<img width="1920" height="1080" alt="22 credentials_Set" src="https://github.com/user-attachments/assets/5d606d2d-087b-4bc0-89dd-8b4bd123a6fe" />
<img width="1920" height="1080" alt="23 Set_credentials_password" src="https://github.com/user-attachments/assets/c4c80c56-4cda-4709-a9be-5fbfddbb570a" />
<img width="1920" height="1080" alt="24 credentials_Set_Page" src="https://github.com/user-attachments/assets/e948b419-d789-4f3e-9890-07259de1d977" />
<img width="1920" height="1080" alt="25 Realm_Session_Settings" src="https://github.com/user-attachments/assets/f2adcde3-15ca-4dd4-a2da-01347df2155e" />
<img width="1920" height="2594" alt="26 Realm_Token_Settings" src="https://github.com/user-attachments/assets/fb0415b3-d73e-4d72-a1ce-e97cb90b6799" />
<img width="1920" height="1080" alt="Ctr+Alt+Shift+S_to_Set_jdk" src="https://github.com/user-attachments/assets/baf65656-9e5b-4f69-9653-09eb5804b671" />
<img width="1920" height="1080" alt="Keyclock_key_in_Backend" src="https://github.com/user-attachments/assets/abd00151-83e2-41ab-b0bf-12685ea8ce37" />

---

### Step 3: Backend Microservices Setup

#### 1. Folder Structure & Modules

Make sure all microservices folders are present as shown below:

#### 📸 Backend Folder Directory (IntelliJ IDEA)

> *IntelliJ project structure screenshot:*
> <img width="1920" height="1080" alt="1 Backend_Folders" src="https://github.com/user-attachments/assets/df7ff911-0c64-44bb-8681-2feb72f02fcf" />


#### 2. Startup Sequence

Start the Spring Boot microservices in this strict sequence:

1. **Eureka Discovery Server** (`eurekaserver`) — *Wait until fully UP*
2. **API Gateway Server** (`gateway-server`)
3. **Domain Microservices**:
* `user-service`
* `business`
* `service-offering`
* `category`
* `booking`
* `payment`
* `review`
* `notifications`



#### 📸 Microservices Running in IntelliJ Services Dashboard

> *IntelliJ Services dashboard screenshot showing all services running:*
<img width="1920" height="1080" alt="2 Backend_Running" src="https://github.com/user-attachments/assets/e9576a16-e6ba-4e35-9670-ddaa60f384b3" />

---

### Step 4: Frontend Setup (React.js)

#### 1. Folder Structure

Open the `frontend` directory in VS Code:

#### 📸 Frontend Directory Structure (VS Code)

> *VS Code project tree screenshot:*
> <img width="1920" height="1080" alt="Frontend_Folder_Structure" src="https://github.com/user-attachments/assets/f77bcd85-d5ba-485e-aa01-abf87d0af474" />


#### 2. Install Dependencies & Start Server

In the VS Code terminal or command prompt:

```bash
cd frontend
npm install
npm start

```
<img width="1920" height="1080" alt="Frontend_npm_i" src="https://github.com/user-attachments/assets/65f12149-0477-492c-8c45-b26f070b35de" />

#### 📸 Frontend Running in Terminal

> *frontend npm start screenshot:*
<img width="1920" height="1080" alt="Frontend_npm_start" src="https://github.com/user-attachments/assets/6f155d8a-be6a-41f7-975e-7ca674c6d13d" />

The application will launch automatically at `http://localhost:3000`.

---

## 🖥️ Visual Application Walkthrough & User Flows

Follow through the main functional pages of AppointCenter:

### 1. User Authentication & Registration

Users can register as a Customer or Partner, or log in with credentials validated via Keycloak.

#### 📸 Customer Registration & Login

> *Register and Login modal screenshots:*
<img width="1920" height="869" alt="3 User_Register_Page" src="https://github.com/user-attachments/assets/d2fa41ef-0088-4d3e-b7bd-4b4c1acc1ba6" />
<img width="1920" height="869" alt="4 User_Login_Page" src="https://github.com/user-attachments/assets/f22eb06f-5d22-4f11-ac2c-85279d3761d4" />

---

### 2. Partner / Business Owner Onboarding

Business owners (e.g. Hospitals, Salons, Clinics) can register via the multi-step "Become Partner" workflow:

* **Step 1: Owner Details** (Full name, Email, Password)
* **Step 2: Business Details** (Business name, Opening & Closing hours)
* **Step 3: Business Address** (Street, City, Pincode, Mobile number)

#### 📸 Multi-Step Partner Registration

> *Become Partner wizard screenshots:*
<img width="1920" height="799" alt="1 Business_Owner_Home_Page" src="https://github.com/user-attachments/assets/3f718f66-a128-4270-a352-84c468d48c8e" />
<img width="1920" height="869" alt="2 Business_Owner_Registration_Page1" src="https://github.com/user-attachments/assets/ea9f6254-e6e1-4fc1-853a-a365db8a923e" />
<img width="1920" height="921" alt="3 Business_Owner_Registration_Page2" src="https://github.com/user-attachments/assets/3d39bf4f-a25d-4df6-bad8-4f702b64dceb" />
<img width="1920" height="869" alt="4 Business_Owner_Registration_Page3" src="https://github.com/user-attachments/assets/22b6e752-c646-43b0-a7ec-d0b12c3fc20d" />
<img width="1920" height="869" alt="5 Business_Owner_Login_Page" src="https://github.com/user-attachments/assets/4df5b04a-1119-4e6a-b7fb-bca172c6a6f8" />

---

### 3. Business Owner Dashboard & Service Setup

Once registered, business owners can access their dedicated portal to:

* Monitor analytics (Total Earnings, Total Bookings, Total Refunds, Cancelled Bookings).
* Create business categories (e.g. Heart, Brain, Hair Care).
* Add specific services, pricing, and appointment durations (in minutes).
* Manage customer bookings and review transaction logs.

#### 📸 Business Owner Dashboard & Analytics

> *Business Booking dashboard and profile screenshots:*
<img width="1920" height="869" alt="6 Business_Owner_Dashboard_Page1" src="https://github.com/user-attachments/assets/276770ee-190f-4365-91f6-a1bbb7ae7767" />
<img width="1920" height="913" alt="7 Business_Owner_Dashboard_Page2" src="https://github.com/user-attachments/assets/bc60b6d6-58bc-48a0-8c9f-a749058136f0" />
<img width="5120" height="3042" alt="8 Business_Owner_account_Page" src="https://github.com/user-attachments/assets/ad1493cb-5b33-4143-a756-78dad86459e3" />

#### 📸 Category & Service Management

> *Add Category and Add Service screenshots:*
> <img width="1920" height="913" alt="9 Business_Create_New_Category_Page" src="https://github.com/user-attachments/assets/9a0298be-b324-442a-9d2c-dcc98d8960b9" />
<img width="1920" height="913" alt="10 Business_Category_List_Page jpg" src="https://github.com/user-attachments/assets/9e1368de-932c-49db-806f-263aa1507f60" />
<img width="1920" height="913" alt="11 Business_Add_Service_Page" src="https://github.com/user-attachments/assets/419ae29f-1a88-4a65-bf7b-45fb8471ecb0" />
<img width="1920" height="913" alt="12 Business_Services_List_Page" src="https://github.com/user-attachments/assets/5b025527-00e5-433c-9523-fcfa1b9262d6" />


#### 📸 Booking List & Transaction History

> *Vendor Booking lists and Transaction tables screenshots:*
<img width="1920" height="913" alt="13 Business_Bookings_Page" src="https://github.com/user-attachments/assets/b19bc146-f8d9-413f-820b-dbede0a872fc" />
<img width="1920" height="913" alt="14 Business_Payment_List_Page" src="https://github.com/user-attachments/assets/a036a393-271f-4d2d-849e-af4a02abe60d" />
<img width="1920" height="913" alt="15 Business_Transactions_Page" src="https://github.com/user-attachments/assets/b97713ed-c037-4928-90b2-e7211f9fa7a2" />

---

### 4. Customer Discovery & Slot Booking

Customers can explore various categories, view business details (like *Amba Health Centre & Hospital*), read customer reviews, and select consultation services.

#### 📸 Home Page & Category Navigation

> *Home Page screenshot:*
<img width="1920" height="3056" alt="0 Home_Page" src="https://github.com/user-attachments/assets/7c66db48-4f6a-482c-a6d2-84d0ea689686" />

#### 📸 Business Details & Service Selection

> *Service listing and Cart drawer screenshots:*
> <img width="1920" height="2385" alt="5 User_Services_Page1" src="https://github.com/user-attachments/assets/e759c12c-e83c-4b03-882b-cb147f2bcdf0" />
<img width="1920" height="2385" alt="6 User_Services_Page2" src="https://github.com/user-attachments/assets/0377abf5-3e59-4d16-a9d7-7006ccdf3a98" />


#### 📸 Interactive Date & Time Slot Picker

Customers select preferred appointment dates and real-time open time slots (e.g., 04:15 PM):
<img width="1920" height="1080" alt="9 Select_Booking_date_Page1" src="https://github.com/user-attachments/assets/3b495421-b717-4003-b7c3-919b73a204a1" />
<img width="1920" height="1080" alt="10 Select_Booking_date_Page2" src="https://github.com/user-attachments/assets/ddc64af4-9011-4fc7-b60a-af515944d143" />

---

### 5. Razorpay Checkout & Order Confirmation

Appointments are secured via Razorpay Payment Gateway integration:

1. Customer initiates booking.
2. Razorpay checkout modal opens supporting UPI, Cards, Net Banking, and QR Codes.
3. Upon payment capture, an animated "Payment Successful" and "Congratulations" confirmation screen is shown.

#### 📸 Razorpay Payment Flow & Success Confirmation

> *Razorpay modal, processing, and success screenshots:*
> <img width="1920" height="988" alt="11 User_razorpay_Payment_Option_Page" src="https://github.com/user-attachments/assets/1e387fa9-4850-47e5-8a75-941155615511" />
<img width="1920" height="988" alt="12 User_razorpay_Processing_Page" src="https://github.com/user-attachments/assets/fcb70f3a-9a13-4d73-9fae-9b2d23b19ed2" />
<img width="1920" height="988" alt="13 User_razorpay_Payment_Complate_Page" src="https://github.com/user-attachments/assets/f9e06580-e59e-40eb-b04c-96300c3a1082" />
<img width="1920" height="1388" alt="14 User_Payment_Success_Page" src="https://github.com/user-attachments/assets/9ae96686-b9ff-48f3-a7c5-875b3b9130b3" />


---

### 6. Customer My Bookings & Reviews

Customers can review their full appointment history, check booking statuses (`PENDING`, `CANCELLED`), cancel bookings, and leave star reviews with written feedback.

#### 📸 My Bookings Page

> *My Bookings screenshots:*
<img width="1920" height="1475" alt="15 User_Bookings_Page jpg" src="https://github.com/user-attachments/assets/39efc06a-cd6d-4501-8fa4-20ea7cb7ba8e" />
<img width="1920" height="1475" alt="16 User_Booking_Cancelled_Page" src="https://github.com/user-attachments/assets/c9ed8073-78d9-474d-844f-98da2572b900" />

#### 📸 Ratings & Reviews

> *Create Review and Customer Rating breakdown screenshots:*
<img width="1920" height="1910" alt="7 User_Create_Review_Page" src="https://github.com/user-attachments/assets/e4a5770e-9fdf-40c2-bb3e-bd6594415505" />
<img width="1920" height="2050" alt="8 User_Review_Page" src="https://github.com/user-attachments/assets/d0650727-2bc6-4b13-9ea1-0300bdd8e994" />

---

## 🌐 Service Port Registry

| Component / Microservice | Port | Description |
| --- | --- | --- |
| **Frontend Web App** | `3000` | React.js SPA Client UI |
| **API Gateway** | `8080` / `8222` | Unified Gateway routing hub |
| **Keycloak IAM** | `8081` | OpenID Connect / OAuth2 Identity Provider |
| **Eureka Server** | `8761` | Netflix Eureka Service Registry & Discovery |
| **User Service** | `8082` | User profiles & account credentials |
| **Business Service** | `8083` | Vendor profiles, addresses & working hours |
| **Service Offering** | `8084` | Offerings catalog, duration & pricing |
| **Booking Service** | `8085` | Slot availability checking & scheduling |
| **Payment Service** | `8086` | Razorpay gateway integration & refunds |
| **Review Service** | `8087` | Star ratings & feedback management |
| **Notification Service** | `8088` | Email / SMS alert dispatchers |

---

## 🔒 Security & Role-Based Access Control

* **Token-Based Authentication**: All endpoints behind the API Gateway validate JWT tokens issued by Keycloak.
* **Roles & Permissions**:
* `CUSTOMER`: Can browse businesses, select time slots, pay via Razorpay, review appointments, and cancel their bookings.
* `SALON OWNER` / `BUSINESS OWNER`: Can configure services, set slot durations, access revenue analytics, and process bookings.
* `ADMIN`: Complete administrative control over users, vendors, and platform metrics.



---

## 🤝 Contributing

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/NewFeature`).
3. Commit your changes (`git commit -m 'Add some NewFeature'`).
4. Push to the branch (`git push origin feature/NewFeature`).
5. Open a Pull Request.

---

## 📄 License & Author
```
Distributed under the MIT License. Developed by **Abhishek Jamkar**.
```
