📊 Finance Data Processing & Access Control Backend

A role-based backend system built using Java Spring Boot + Oracle SQL, designed to manage financial records and provide dashboard analytics with secure access control.

🚀 Objective

This project demonstrates backend development skills including:

API design
Data modeling
Business logic implementation
Role-based access control (RBAC)
Data aggregation for dashboards
🛠️ Tech Stack
Backend: Java, Spring Boot
Database: Oracle SQL
ORM: Spring Data JPA (Hibernate)
Frontend: HTML, CSS, JavaScript
Build Tool: Maven
📂 Project Structure
com.finance.dashboard
│
├── controller      → REST APIs
├── service         → Business logic
├── repository      → Database access
├── model           → Entity classes
├── config          → CORS & configuration
└── DashboardApplication.java
👤 Roles & Access Control
Role	Permissions
ADMIN	Full access (Create, Read, Update, Delete, Summary)
ANALYST	View records + dashboard summary
VIEWER	View dashboard summary only

👉 Access is enforced at backend level using role checks

📌 Features
1. User & Role Handling
Role passed via request header (role)
Backend enforces access restrictions
2. Financial Records Management

Each record contains:

Amount
Type (INCOME / EXPENSE)
Category
Date
Description

Supported operations:

Create record
View records
Update record
Delete record
3. Dashboard Summary

APIs provide:

Total Income
Total Expense
Net Balance
4. Access Control
Viewer → only summary
Analyst → summary + records
Admin → full control
5. Validation & Error Handling
Handles missing headers (role)
Prevents unauthorized actions
Returns proper HTTP status codes
🔗 API Endpoints
🔹 Create Record
POST /api/finance/create
Header: role = ADMIN
🔹 Get All Records
GET /api/finance/all
Header: role = ADMIN / ANALYST
🔹 Update Record
PUT /api/finance/update/{id}
Header: role = ADMIN
🔹 Delete Record
DELETE /api/finance/delete/{id}
Header: role = ADMIN
🔹 Get Summary
GET /api/finance/summary
Header: role = ANY
⚙️ Setup Instructions
1️⃣ Clone Repository
git clone <your-repo-link>
cd dashboard
2️⃣ Configure Database

Update application.properties:

spring.datasource.url=jdbc:oracle:thin:@localhost:1521:orcl
spring.datasource.username=system
spring.datasource.password=system

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3️⃣ Run Backend
mvn spring-boot:run

Server starts at:

http://localhost:8080
4️⃣ Run Frontend
Open project in VS Code
Run using Live Server
Open:
http://localhost:5500
🧪 Testing

Use Postman:

Example:

GET http://localhost:8080/api/finance/all
Header:
role = ADMIN
⚠️ Assumptions
Authentication is simplified (role passed via header)
No password/login system implemented
Data validation is basic
Single-user local environment
⚖️ Trade-offs
Used header-based role system instead of JWT for simplicity
No pagination implemented (kept minimal for clarity)
Oracle DB used as per requirement, though lighter DB could be faster for testing
Focused on backend logic rather than production-level security
💡 Future Improvements
JWT Authentication
Pagination & filtering
Charts for dashboard
Audit logs
Unit & integration tests
🎯 Key Highlights
Clean layered architecture
Strong role-based access control
Proper separation of concerns
Real-world backend design approach
👨‍💻 Author

Lawish Deshbhartar

Java Backend Developer
Skilled in Spring Boot, REST APIs, SQL
⭐ Conclusion

This project showcases a well-structured backend system with clear business logic, secure access control, and scalable design suitable for financial applications.
