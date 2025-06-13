
INSERT INTO "user" (username, password, role) VALUES 
('admin', '$2a$12$0QGGaZC6MSiLh64XK2I1peeqwaA2xYq.tCFHoZ3.xxjTl3oisslSa', 'ROLE_ADMIN'),
('vendor1', '$2a$12$0QGGaZC6MSiLh64XK2I1peeqwaA2xYq.tCFHoZ3.xxjTl3oisslSa', 'ROLE_VENDOR');

INSERT INTO vendor (vendor_name, subscription_type) VALUES 
('SpeedyLogistics', 'PRIME'),
('QuickShip', 'NORMAL'),
('ExpressZone', 'VIP');
