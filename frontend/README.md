# 🎨 Business Booking Frontend (React)

A modern, responsive **frontend application** for a business booking platform built with **React, Redux, and Tailwind CSS**.

This app allows customers, business owners, and admins to interact with the system through an intuitive UI with real-time updates and seamless booking experience.

---

# 🚀 Tech Stack

- ⚛️ React.js
- 🗂 Redux (State Management)
- 🎨 Tailwind CSS
- 🔗 Axios (API Communication)
- 🔔 WebSocket (Real-time Notifications)

---

# 📁 Project Structure

Based on the current project structure:

```bash
src/
├── Admin/                # Admin dashboard & management
├── Auth/                 # Authentication (Login, Register, Reset)
├── Customer/             # Customer-facing UI
├── Redux/                # Global state management
├── routes/               # Route configurations
├── business/                # Business owner dashboard
├── util/                 # Utility functions
├── config/               # API configuration
└── App.js                # Root component
```

---

# 👤 User Roles & Features

## 👥 Customer

- Browse businesses
- View services and details
- Book appointments
- Make payments
- Receive notifications
- Write reviews

## 🏢 Business Owner

- Manage business profile
- Add/update services
- View bookings
- Track earnings

## 🛠 Admin

- Dashboard overview
- Manage businesses
- Monitor activity

---

# 🔐 Authentication

- Login & Registration system
- Password reset flow
- Role-based access routing
- Integrated with backend authentication (Keycloak)

---

# 🔄 State Management (Redux)

Organized into modules:

- Auth
- Booking
- Category
- Business
- Payment
- Notifications
- Reviews

Each module contains:

- actions
- reducers
- actionTypes

---

# 🌐 Routing

Custom route handling for:

- Admin routes
- Customer routes
- Business routes

Ensures:

- Protected routes
- Role-based navigation

---

# 📡 API Integration

Configured in:

```bash
src/config/api.js
```

- Centralized API calls
- Backend communication via Axios

---

# 🔔 Real-Time Notifications

- Implemented using WebSocket
- Custom hook:

```bash
useNotificationWebsocket.jsx
```

- Enables live updates for bookings and events

---

# 💳 Payment Integration

- Handles payment success flow
- Integrated with backend payment services
- UI handler:

```bash
PaymentSuccessHandler.jsx
```

---

# 🎨 UI & Design

- Tailwind CSS for styling
- Responsive design
- Reusable components
- Clean and modern UI

---

# ⚙️ Setup Instructions

## 1️⃣ Install dependencies

```bash
npm install
```

---

## 2️⃣ Run the application

```bash
npm start
```

App runs at:

```bash
http://localhost:3000
```

---

# 🔧 Environment Configuration

Create `.env` file in frontend root:

```env
REACT_APP_API_URL=http://localhost:8080
```

---

# 🧪 Testing

```bash
npm test
```

---

# 🚀 Future Improvements

- Dark mode support
- Performance optimization
- PWA support
- Better UI animations
- Advanced filtering & search

---

# 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Open a Pull Request

---

# 📄 License

MIT License

---

# 🙌 Acknowledgements

- React
- Redux
- Tailwind CSS

---

# 📬 Contact

Feel free to connect for collaboration or feedback.
