# 🛒 Jupitech - Tech E-commerce Platform

<div align="center">

![Jupitech Logo](https://res.cloudinary.com/dmpjrbf97/image/upload/w_200,h_100,c_fill/JupitechLogo_zv7imp.png)

**A Modern Full-Stack E-commerce Platform for Tech Enthusiasts**

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)](https://reactjs.org/)
[![Next.js](https://img.shields.io/badge/Next.js-000000?style=for-the-badge&logo=next.js&logoColor=white)](https://nextjs.org/)
[![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)](https://tailwindcss.com/)
[![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)

</div>

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Security](#-security)
- [Contributing](#-contributing)
- [License](#-license)

## 🌟 Overview

**Jupitech** is a comprehensive e-commerce platform specifically designed for technology enthusiasts. Built with modern web technologies, it provides a seamless shopping experience for tech items and devices. The platform features a robust backend API, responsive frontend interface, and secure authentication system.

### Key Highlights
- 🚀 **Full-Stack Solution**: Complete e-commerce functionality from product browsing to order management
- 🔐 **Secure Authentication**: JWT-based authentication with Spring Security
- 📱 **Responsive Design**: Mobile-first approach using Tailwind CSS
- 🛡️ **Enterprise-Grade Security**: Role-based access control and data protection
- ⚡ **High Performance**: Optimized with Next.js and efficient database queries

## ✨ Features

### 🛍️ Customer Features
- **Product Catalog**: Browse extensive collection of tech products
- **Advanced Search & Filtering**: Find products by category, price, brand, and specifications
- **Shopping Cart**: Add, remove, and modify cart items
- **Secure Checkout**: Multiple payment options with secure processing
- **Order Tracking**: Real-time order status updates
- **User Profiles**: Manage personal information and order history
- **Wishlist**: Save favorite products for later
- **Product Reviews**: Rate and review purchased items

### 👨‍💼 Admin Features
- **Dashboard**: Comprehensive analytics and sales overview
- **Product Management**: Add, edit, and manage product inventory
- **Order Management**: Process and track customer orders
- **User Management**: Manage customer accounts and permissions
- **Sales Reports**: Generate detailed sales and performance reports
- **Inventory Control**: Track stock levels and manage suppliers

### 🔧 Technical Features
- **RESTful API**: Well-structured API endpoints
- **Real-time Updates**: Live notifications for order status
- **File Upload**: Image management for products
- **Error Handling**: Graceful error management
- **Logging**: Detailed application logging

## 🛠️ Tech Stack

### Backend
- **Framework**: Spring Boot 3.x
- **Security**: Spring Security + JWT Authentication
- **Database**: MySQL 8.0
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Java Version**: Java 17+

### Frontend
- **Framework**: React 18+ with Next.js 14
- **Styling**: Tailwind CSS
- **UI Components**: Custom components with HTML5 & CSS3
- **State Management**: React Context API / Redux Toolkit
- **HTTP Client**: Axios
- **Form Handling**: React Hook Form

### Development & Deployment
- **Version Control**: Git
- **API Testing**: Postman / Swagger
- **Database Management**: MySQL Workbench
- **IDE**: IntelliJ IDEA / VS Code

## 🏗️ Architecture

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│                 │    │                  │    │                 │
│   Frontend      │────│   Backend API    │────│   Database      │
│   (Next.js +    │    │   (Spring Boot)  │    │   (MySQL)       │
│   React +       │    │                  │    │                 │
│   Tailwind)     │    │                  │    │                 │
│                 │    │                  │    │                 │
└─────────────────┘    └──────────────────┘    └─────────────────┘
```

### Database Schema
- **Users**: Customer and admin account information
- **Products**: Tech items with detailed specifications
- **Categories**: Product categorization system
- **Orders**: Customer purchase records
- **Order_Items**: Individual items within orders
- **Reviews**: Product ratings and feedback
- **Cart**: Shopping cart management

## 📋 Prerequisites

Before running this project, make sure you have the following installed:

- **Java 17+** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Node.js 18+** - [Download](https://nodejs.org/)
- **MySQL 8.0+** - [Download](https://www.mysql.com/downloads/)
- **Maven 3.6+** - [Download](https://maven.apache.org/download.cgi)
- **Git** - [Download](https://git-scm.com/)

## 🚀 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/akashi-47/jupitech.git
cd jupitech
```

### 2. Database Setup
```sql
-- Create database
CREATE DATABASE jupitech_db;

-- Create user (optional)
CREATE USER 'jupitech_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON jupitech_db.* TO 'jupitech_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Backend Setup
```bash
# Navigate to backend directory
cd backend

# Install dependencies
mvn clean install

# Run the application
mvn spring-boot:run
```

### 4. Frontend Setup
```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start development server
npm run dev
```



## 🔐 Security

### Authentication Flow
1. **User Registration/Login**: Credentials validated against database
2. **JWT Token Generation**: Secure token created with user information
3. **Token Validation**: Each request validates JWT token
4. **Role-Based Access**: Different permissions for customers and administrators

### Security Features
- **Password Encryption**: BCrypt hashing algorithm
- **CORS Configuration**: Cross-origin resource sharing setup
- **Input Validation**: Server-side validation for all inputs
- **SQL Injection Prevention**: Parameterized queries
- **XSS Protection**: Input sanitization and output encoding

## 🤝 Contributing

We welcome contributions to improve Jupitech! Here's how you can help:

### Getting Started
1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

### Development Guidelines
- Follow the existing code style and conventions
- Write clear, descriptive commit messages
- Add tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR

### Areas for Contribution
- 🐛 Bug fixes
- ✨ New features
- 📚 Documentation improvements
- 🎨 UI/UX enhancements
- ⚡ Performance optimizations
- 🧪 Test coverage improvements



## 👨‍💻 Author

**Ismail Saili**
- GitHub: [@ismail-saili](https://github.com/akashi-47)
- Email: ismail.saili07@gmail.com
- LinkedIn: [Ismail Saili Driouch](https://www.linkedin.com/in/ismail-saili-800bba249/)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- React and Next.js communities for amazing tools
- Tailwind CSS for the utility-first CSS framework
- MySQL for the reliable database system
- All open-source contributors who made this project possible

---

<div align="center">

**⭐ Star this repository if you found it helpful!**

![GitHub stars](https://img.shields.io/github/stars/akashi-47/jupitech?style=social)
![GitHub forks](https://img.shields.io/github/forks/akashi-47/jupitech?style=social)
![GitHub watchers](https://img.shields.io/github/watchers/akashi-47/jupitech?style=social)

</div>
