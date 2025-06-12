-- Encrypted password = 'admin@123'
INSERT INTO "user" (username, password, role) 
VALUES ('admin', '$2a$10$U8DqM9U5gRuZcgnhpBLRwu4Yc1rN3WeP2DZzXkpz1qfJqAX2WaX7K', 'ROLE_ADMIN');

INSERT INTO vendor (vendor_name, subscription_type) VALUES ('SpeedyLogistics', 'PRIME');
INSERT INTO vendor (vendor_name, subscription_type) VALUES ('QuickShip', 'NORMAL');
INSERT INTO vendor (vendor_name, subscription_type) VALUES ('ExpressZone', 'VIP');
