# Assignment 2 – Backend (Delivery Order Upload & View)

This Spring Boot backend module implements secure APIs for file upload, order listing, filtering, and role-based access for the Zero Mile Delivery System.

## ✅ Technologies Used

- Java 21
- Spring Boot 3.5.x
- Spring Data JPA
- Spring Security with JWT
- H2 in-memory database
- Lombok
- Multipart file handling

## 🚀 Features Implemented

### 🔐 Role-Based Access (RBAC)
- JWT-secured endpoints
- Roles: `ROLE_VENDOR`, `ROLE_ADMIN`
- Vendor can upload `.txt` file containing delivery orders.
- Admin or Vendor can view uploaded orders (with pagination and filters).

---

## 📂 Endpoints Summary

### 🔸 POST `/api/orders/upload`
- **Access**: VENDOR only
- **Parameters** (multipart):
  - `vendorName`: Name of the vendor (e.g., `SpeedyLogistics`)
  - `orderDate`: ISO format (`yyyy-MM-dd`)
  - `file`: `.txt` file to upload
- **Example using Postman**:
  - Form-Data
    - `vendorName`: `SpeedyLogistics`
    - `orderDate`: `2025-06-13`
    - `file`: Upload from disk (`.txt` file)

### 🔸 GET `/api/orders`
- **Access**: VENDOR / ADMIN
- **Query Parameters**:
  - `vendorName` (optional)
  - `date` (optional)
  - `page`, `size` (for pagination)
- **Example**: `/api/orders?vendorName=QuickShip&date=2025-06-13&page=0&size=5`

---

## 🗂 Tables Involved

- `delivery_order`: Stores metadata about uploaded orders
- `vendor`: Vendor info (foreign key reference)
- `user`: Used for role-based access

---

## 🛠 How to Run

right click on project 
Run As
Spring boot app
   or
Run As
Maven Build 
goals: mvn spring-boot:run
click on run button 
