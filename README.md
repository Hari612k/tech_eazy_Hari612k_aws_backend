🚚 Zero Mile Delivery System – Backend

This is the Spring Boot backend for the Zero Mile Delivery System, built as part of the AWS Internship Project. It manages parcel tracking, delivery order uploads, JWT-based authentication, and RBAC for Admin & Vendor roles.

🔧 Tech Stack


      Java 21

      Spring Boot 3.x

      Spring Security (JWT-based)

      Spring Data JPA

      H2 in-memory DB

      Maven

      Lombok




✅ Features

      🔐 Authentication

            POST /auth/login – Login with JWT token

            POST /auth/register – Register user (optional)


📦 Parcels – Admin Only


      POST /api/parcels/bulk – Bulk create parcels

      GET /api/parcels – View all parcels

      GET /api/parcels/{trackingNumber} – Get parcel by tracking number

      PUT /api/parcels/update/{id} – Update parcel

      DELETE /api/parcels/delete/{id} – Delete parcel


📁 Delivery Orders – Vendor/Admin


      POST /api/orders/upload – Upload delivery order .csv or .txt file

      GET /api/orders – View delivery orders with optional filters


🌐 Public Access – No Auth Required


      GET /public/parcels/{trackingNumber} – Track parcel without login


📊 Admin Summary

      GET /api/summary/today – Get today’s parcel summary grouped by delivery address



👥 User Roles


      | Role          | Access Privileges                       |
      | ------------- | --------------------------------------- |
      | `ROLE_ADMIN`  | Full access to parcels, orders, summary |
      | `ROLE_VENDOR` | Upload orders and view them             |
      | Public        | Only track parcel by tracking number    |



🧪 Testing Guide


      ✅ Use Postman with the Bearer Token set in Authorization tab

      ✅ Import ZMD_Postman_Collection.json to simplify endpoint testing

      ✅ Upload delivery files in Postman:

            POST /api/orders/upload

            Use form-data

            Key: file, Type: File, choose a .csv or .txt file

            Add vendorName and orderDate (format: yyyy-MM-dd)

      ✅ Use /public/parcels/{trackingNumber} without any token

      ✅ H2 Console: http://localhost:8080/h2-console

            URL: jdbc:h2:mem:zeromile
            User: sa
            Password: [leave empty]


🛠️ Troubleshooting Tips from Real Testing

      ❗ 403 Forbidden – Happens if wrong role used (e.g. using Admin token for Vendor-only upload)

      ❗ 400 Bad Request – Happens if file part is missing or incorrect field name (file must be multipart and correct)

✅ Fix: Use Postman form-data → Key: file, Type: File

🧪 File Upload Alternative:

If facing upload issues, manually insert a delivery_order row via H2 Console with:

      INSERT INTO delivery_order (order_date, file_link, vendor_id)
      VALUES ('2025-06-16', 'uploads/sample.csv', 1);

Then link parcel rows with delivery_order_id = 1.

🗓️ Summary API returns empty:

      GET /api/summary/today only works if order_date = current date. Manually update the date if needed.

✅ After adding valid delivery orders and parcels:

      SELECT * FROM delivery_order;
      SELECT * FROM parcel WHERE delivery_order_id = 1;



▶️ Run the Project

1. Clone the project
      git clone https://github.com/Hari612k/tech_eazy_Hari612k_aws_backend.git

2. Open in your IDE (Eclipse/IntelliJ)

3. Run: ZeroMileDeliveryApplication.java

      Right-click → Run As → Spring Boot App
