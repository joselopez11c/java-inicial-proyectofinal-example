CREATE DATABASE ventas;

CREATE TABLE ventas.cliente (
    dni int NOT NULL,
    nombre varchar(50) NOT NULL,
    apellido varchar(50),
    PRIMARY KEY (dni)
);

CREATE TABLE ventas.producto (
	productoid int NOT NULL AUTO_INCREMENT,
    codigo int NOT NULL,
    descripcion varchar(255) NOT NULL,
    stock int,
    precio FLOAT(10, 2),
    PRIMARY KEY (productoid)
);

CREATE TABLE ventas.comprobante (
    comprobanteid int NOT NULL AUTO_INCREMENT,
    fecha DATETIME,
    cantidad int,
    total FLOAT(10, 2),
    clienteid int,
    estado int,
    PRIMARY KEY (comprobanteid),
    CONSTRAINT FK_cliente FOREIGN KEY (clienteid)
    REFERENCES cliente(dni)
);

CREATE TABLE ventas.item (
    itemid int NOT NULL AUTO_INCREMENT,
    descripcion varchar(255) NOT NULL,
    cantidad int,
    precio FLOAT(10, 2),
    comprobanteid int NOT NULL,
    productoid int NOT NULL,
    PRIMARY KEY (itemid),
    CONSTRAINT FK_comprobante FOREIGN KEY (comprobanteid)
    REFERENCES comprobante(comprobanteid),
    CONSTRAINT FK_producto FOREIGN KEY (productoid)
    REFERENCES producto(productoid)
);