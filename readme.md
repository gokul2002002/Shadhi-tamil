![Shadhi Tamil](src/main/resources/static/logo.png)

# **Shadhi Tamil - Matrimony Platform**

## **Overview**
Shadhi Tamil is a feature-rich, secure, and scalable matrimony platform designed to connect Tamil-speaking individuals seeking life partners. Built using **Spring Boot** for the backend, it ensures high performance, security, and seamless user experience.

## **Tech Stack**
- **Backend:** Spring Boot, Spring Security, JWT Authentication
- **Database:** MySQL
- **Caching:** Redis
- **Storage:** MinIO (for media uploads)
- **Deployment:** Docker, GitLab CI/CD
- **Messaging:** Spring Mail (for OTP verification)

## **Key Features**
✅ **User Authentication**: Secure login/signup with JWT and OTP verification.  
✅ **Profile Management**: Users can create, update, and manage their profiles.  
✅ **Advanced Matchmaking**: AI-powered recommendation engine for profile matching.  
✅ **Image & Document Uploads**: Securely store user-uploaded images using MinIO.  
✅ **Admin Dashboard**: Role-based access control for managing users and reports.  
✅ **Redis Caching**: Fast OTP storage and retrieval for enhanced user experience.  
✅ **Real-time Notifications**: Instant updates using Firebase/Socket.io.

## **Installation & Setup**
### **1. Clone the Repository**
```sh
git clone https://github.com/your-repo/shadhi-tamil.git
cd shadhi-tamil
```

### **2. Configure the Environment**
Create an `.env` file or update `application.yml` with your database, Redis, and MinIO credentials.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/matrimony
    username: root
    password: minipass
  redis:
    host: localhost
    port: 9003
  security:
    secret-key: HelloDear
minio:
  endpoint: http://localhost:9000
  access-key: your-access-key
  secret-key: your-secret-key
  bucket-name: shadhi-bucket
```

### **3. Run Redis in Docker**
```sh
docker run --name redis-container -p 9003:6379 -d redis
```

### **4. Start the Application**
```sh
mvn clean spring-boot:run
```

## **Contributing**
We welcome contributions! Please follow the **Git Flow** model and submit a PR.

## **License**
MIT License. See `LICENSE` for more details.

---
### **🚀 Shadhi Tamil - Bringing Hearts Together ❤️**

