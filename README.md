# Zero Mile Delivery System – Backend

This Spring Boot backend project, it handles parcel tracking, delivery order uploads, user authentication (JWT), and role-based access control (RBAC).

##  Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Security + JWT
- Spring Data JPA
- H2 in-memory DB
- Lombok
- Maven


##  Features

### Authentication

- `POST /auth/login` – Login and receive a JWT
- `POST /auth/register` – Register a new user (optional)

### Parcels (Admin only)

- `POST /api/parcels` – Create a parcel
- `POST /api/parcels/bulk` – Bulk upload parcels
- `GET /api/parcels` – View all parcels
- `GET /api/parcels/{trackingNumber}` – View parcel by tracking ID
- `PUT /api/parcels/update/{id}` – Update a parcel
- `DELETE /api/parcels/delete/{id}` – Delete a parcel

### Delivery Orders (Vendor/Admin)

- `POST /api/orders/upload` – Upload order file (Admin/Vendor)
- `GET /api/orders` – View paginated and filtered orders (Admin/Vendor)

### Public Access

- `GET /public/parcels/{trackingNumber}` – Track parcel without login

### Admin Summary

- `GET /api/summary/today` – View today’s parcel summary by delivery address



##  User Roles

- `ROLE_ADMIN`: All access
- `ROLE_VENDOR`: Upload orders, view orders
- Public: Track parcel only

##  Testing

- Import `ZMD_Postman_Collection.json` &  in Postman
- Use endpoints with token-based authorization
- Test public tracking with no token

##  Run the Project

1. Clone and open in your IDE (Eclipse/IntelliJ)
2. Run `ZeroMileDeliveryApplication.java`
3. Open: `http://localhost:8080/h2-console`
   - URL: `jdbc:h2:mem:zeromile`
   - User: `sa`, Password: *(leave empty)*
