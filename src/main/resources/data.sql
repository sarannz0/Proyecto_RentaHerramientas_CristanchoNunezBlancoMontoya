-- Script de inicialización de roles
-- Este script se ejecuta automáticamente al iniciar la aplicación

-- Insertar roles iniciales si no existen
-- Para MySQL con UUID como CHAR(36)
INSERT IGNORE INTO roles (id, name, authority) 
VALUES 
    ('550e8400-e29b-41d4-a716-446655440001', 'USER', 'ROLE_USER'),
    ('550e8400-e29b-41d4-a716-446655440002', 'ADMIN', 'ROLE_ADMIN'),
    ('550e8400-e29b-41d4-a716-446655440003', 'MODERATOR', 'ROLE_MODERATOR');

INSERT IGNORE INTO countries (id, name, isocode) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'Antigua y Barbuda', 'ATG'),
('550e8400-e29b-41d4-a716-446655440002', 'Argentina', 'ARG'),
('550e8400-e29b-41d4-a716-446655440003', 'Bahamas', 'BHS'),
('550e8400-e29b-41d4-a716-446655440004', 'Barbados', 'BRB'),
('550e8400-e29b-41d4-a716-446655440005', 'Belice', 'BLZ'),
('550e8400-e29b-41d4-a716-446655440006', 'Bolivia', 'BOL'),
('550e8400-e29b-41d4-a716-446655440007', 'Brasil', 'BRA'),
('550e8400-e29b-41d4-a716-446655440008', 'Canadá', 'CAN'),
('550e8400-e29b-41d4-a716-446655440009', 'Chile', 'CHL'),
('550e8400-e29b-41d4-a716-446655440010', 'Colombia', 'COL'),
('550e8400-e29b-41d4-a716-446655440011', 'Costa Rica', 'CRI'),
('550e8400-e29b-41d4-a716-446655440012', 'Cuba', 'CUB'),
('550e8400-e29b-41d4-a716-446655440013', 'Dominica', 'DMA'),
('550e8400-e29b-41d4-a716-446655440014', 'Ecuador', 'ECU'),
('550e8400-e29b-41d4-a716-446655440015', 'El Salvador', 'SLV'),
('550e8400-e29b-41d4-a716-446655440016', 'Estados Unidos', 'USA'),
('550e8400-e29b-41d4-a716-446655440017', 'Granada', 'GRD'),
('550e8400-e29b-41d4-a716-446655440018', 'Guatemala', 'GTM'),
('550e8400-e29b-41d4-a716-446655440019', 'Guyana', 'GUY'),
('550e8400-e29b-41d4-a716-446655440020', 'Haití', 'HTI'),
('550e8400-e29b-41d4-a716-446655440021', 'Honduras', 'HND'),
('550e8400-e29b-41d4-a716-446655440022', 'Jamaica', 'JAM'),
('550e8400-e29b-41d4-a716-446655440023', 'México', 'MEX'),
('550e8400-e29b-41d4-a716-446655440024', 'Nicaragua', 'NIC'),
('550e8400-e29b-41d4-a716-446655440025', 'Panamá', 'PAN'),
('550e8400-e29b-41d4-a716-446655440026', 'Paraguay', 'PRY'),
('550e8400-e29b-41d4-a716-446655440027', 'Perú', 'PER'),
('550e8400-e29b-41d4-a716-446655440028', 'República Dominicana', 'DOM'),
('550e8400-e29b-41d4-a716-446655440029', 'San Cristóbal y Nieves', 'KNA'),
('550e8400-e29b-41d4-a716-446655440030', 'San Vicente y las Granadinas', 'VCT'),
('550e8400-e29b-41d4-a716-446655440031', 'Santa Lucía', 'LCA'),
('550e8400-e29b-41d4-a716-446655440032', 'Surinam', 'SUR'),
('550e8400-e29b-41d4-a716-446655440033', 'Trinidad y Tobago', 'TTO'),
('550e8400-e29b-41d4-a716-446655440034', 'Uruguay', 'URY'),
('550e8400-e29b-41d4-a716-446655440035', 'Venezuela', 'VEN');