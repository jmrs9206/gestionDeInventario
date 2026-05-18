
INSERT INTO usuarios (nombre, email, password, estado)
VALUES ('ADMINISTRADOR', 'admin@email.com', '1234', 'ALTA'),
       ('TECNICO', 'tecnico@email.com', '4321', 'ALTA');


INSERT INTO oficinas (nombre, estado)
VALUES ('MADRID', 'ALTA'),
       ('VALENCIA', 'ALTA'),
       ('SALAMANCA', 'ALTA');


INSERT INTO materiales (tipo, marca, modelo, descripcion, estado, id_oficina)
VALUES ('RATON', 'LOGITECH', 'K120', 'RATON ESTANDAR', 'ALTA', 1),
       ('TECLADO', 'LENOVO', 'T500', 'TECLADO BASICO', 'ALTA', 2),
       ('MONITOR', 'DELL', 'P2419H', 'PANTALLA 24 PULGADAS', 'ROTO', 3),
       ('PORTATIL', 'HP', 'PROBOOK', 'EQUIPO DESARROLLO', 'BAJA', 1);


INSERT INTO historial_materiales (id_material, id_oficina, id_usuario, estado, observacion)
VALUES (1, 1, 1, 'ALTA', 'REGISTRO INICIAL'),
       (1, 2, 2, 'ALTA', 'TRASLADO A SEDE VALENCIA POR PETICION');


INSERT INTO historial_materiales (id_material, id_oficina, id_usuario, estado, observacion)
VALUES (3, 3, 2, 'ALTA', 'ENTRADA NUEVA'),
       (3, 3, 1, 'ROTO', 'PANTALLA GOLPEADA DURANTE MUDANZA');


INSERT INTO historial_materiales (id_material, id_oficina, id_usuario, estado, observacion)
VALUES (4, 1, 1, 'ALTA', 'EQUIPO ANTIGUO'),
       (4, 1, 1, 'BAJA', 'OBSOLETO - RETIRADO DEL INVENTARIO');
