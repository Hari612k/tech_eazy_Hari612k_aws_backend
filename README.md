 Zero Mile Delivery System – Backend (Assignment 4)

This Spring Boot backend handles parcel tracking, delivery order uploads, user authentication (JWT), and role-based access control (RBAC). AWS EC2 is used for deployment.

## 🚀 Tech Stack
- Java 21
- Spring Boot 3.x
- Spring Security + JWT
- Spring Data JPA
- H2 in-memory DB
- Lombok
- Maven

## 🌐 Deployment
- Hosted on **AWS EC2 Instance**
- EC2 Public IP: `http://<YOUR_EC2_PUBLIC_IP>:8080`

---

## 🔑 Authentication Endpoints
- `POST /auth/login` – Login and receive a JWT
- `POST /auth/register` – Register a new user (optional)

## 📦 Parcel Endpoints (Admin Only)
- `POST /api/parcels/bulk` – Bulk upload parcels
- `GET /api/parcels` – View all parcels
- `GET /api/parcels/{trackingNumber}` – View parcel by tracking ID
- `PUT /api/parcels/update/{id}` – Update a parcel
- `DELETE /api/parcels/delete/{id}` – Delete a parcel

## 🗂️ Delivery Orders (Vendor/Admin)
- `POST /api/orders/upload` – Upload delivery order files
- `GET /api/orders` – View paginated and filtered orders

## 📂 Public Access
- `GET /public/parcels/{trackingNumber}` – Track parcel without login

## 📊 Admin Summary
- `GET /api/summary/today` – Parcel summary grouped by address for today's orders

---

## 📝 Notes
- **If file upload via Postman fails, add delivery orders and parcels manually in H2 console for testing.**
- EC2 security group must allow inbound rules for **8080 (HTTP port)**.

---

## 🧪 Testing with Postman
- Import `ZMD_Postman_Collection_Assignment4.json`
- Update base URLs to: `http://<YOUR_EC2_PUBLIC_IP>:8080`
- Authenticate first to receive JWT token.
- Test all endpoints using Bearer Token.
- Public endpoint (`/public/parcels/{trackingNumber}`) works without authentication.

---

## ⚙️ Running the Project on AWS
1. SSH into EC2:
   ```bash
   ssh -i "your-key.pem" ec2-user@<YOUR_EC2_PUBLIC_IP>
## ⚙️ Running the Project on AWS
1. SSH into EC2:
   ```bash
   ssh -i "your-key.pem" ec2-user@<YOUR_EC2_PUBLIC_IP># Zero Mile Delivery System – Backend (Assignment 4)

This Spring Boot backend handles parcel tracking, delivery order uploads, user authentication (JWT), and role-based access control (RBAC). AWS EC2 is used for deployment.

## 🚀 Tech Stack
- Java 21
- Spring Boot 3.x
- Spring Security + JWT
- Spring Data JPA
- H2 in-memory DB
- Lombok
- Maven

## 🌐 Deployment
- Hosted on **AWS EC2 Instance**
- EC2 Public IP: `http://<YOUR_EC2_PUBLIC_IP>:8080`

---

## 🔑 Authentication Endpoints
- `POST /auth/login` – Login and receive a JWT
- `POST /auth/register` – Register a new user (optional)

## 📦 Parcel Endpoints (Admin Only)
- `POST /api/parcels/bulk` – Bulk upload parcels
- `GET /api/parcels` – View all parcels
- `GET /api/parcels/{trackingNumber}` – View parcel by tracking ID
- `PUT /api/parcels/update/{id}` – Update a parcel
- `DELETE /api/parcels/delete/{id}` – Delete a parcel

## 🗂️ Delivery Orders (Vendor/Admin)
- `POST /api/orders/upload` – Upload delivery order files
- `GET /api/orders` – View paginated and filtered orders

## 📂 Public Access
- `GET /public/parcels/{trackingNumber}` – Track parcel without login

## 📊 Admin Summary
- `GET /api/summary/today` – Parcel summary grouped by address for today's orders

---

## 📝 Notes
- **If file upload via Postman fails, add delivery orders and parcels manually in H2 console for testing.**
- EC2 security group must allow inbound rules for **8080 (HTTP port)**.

---

## 🧪 Testing with Postman
- Import `ZMD_Postman_Collection_Assignment4.json`
- Update base URLs to: `http://<YOUR_EC2_PUBLIC_IP>:8080`
- Authenticate first to receive JWT token.
- Test all endpoints using Bearer Token.
- Public endpoint (`/public/parcels/{trackingNumber}`) works without authentication.

---

## ⚙️ Running the Project on AWS
1. SSH into EC2:
   ```bash
   ssh -i "your-key.pem" ec2-user@<YOUR_EC2_PUBLIC_IP>
   