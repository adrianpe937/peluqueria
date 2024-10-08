CREATE TABLE TRABAJADORES (
    ID_TRABAJADOR INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE VARCHAR(100) NOT NULL,
    APELLIDO VARCHAR(100),
    ACTIVO BOOLEAN NOT NULL,
    ADMIN BOOLEAN,
    COMISION DECIMAL(10, 2),
    ESTADISTICAS VARCHAR(255),
    TELEFONO VARCHAR(20),
    CORREO VARCHAR(100)
);
CREATE TABLE PRODUCTOS (
    ID_PRODUCTO INT PRIMARY KEY AUTO_INCREMENT,
    DESCRIPCION VARCHAR(255) NOT NULL,
    STOCK INT NOT NULL,
    ACTIVO BOOLEAN NOT NULL,
    CODIGO_BARRAS VARCHAR(50),
    PROVEEDOR VARCHAR(100),
    PRECIO_COMPRA DECIMAL(10, 2),
    PRECIO_VENTA DECIMAL(10, 2),
    TIPO_PRODUCTO VARCHAR(50)
);
CREATE TABLE CLIENTES (
    ID_CLIENTE INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE VARCHAR(100) NOT NULL,
    TELEFONO VARCHAR(20),
    LPD BOOLEAN,
    CORREO VARCHAR(100)
);
CREATE TABLE SERVICIOS (
    ID_SERVICIOS INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE VARCHAR(100) NOT NULL,
    DESCRIPCION VARCHAR(255),
    PRECIO DECIMAL(10, 2) NOT NULL
);
CREATE TABLE TRABAJADOR_PRODUCTO (
    ID_TRABAJADOR INT,
    ID_PRODUCTO INT,
    CLIENTE_COMPRADOR VARCHAR(100),
    CANTIDAD INT NOT NULL,
    FECHA DATE NOT NULL,
    PAGADO BOOLEAN,
    METODO_DE_PAGO VARCHAR(50),
    FOREIGN KEY (ID_TRABAJADOR) REFERENCES TRABAJADORES(ID_TRABAJADOR),
    FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID_PRODUCTO)
);
CREATE TABLE TRABAJADOR_SERVICIO (
    ID_TRABAJADOR INT,
    ID_SERVICIO INT,
    ID_CLIENTE INT,
    FECHA DATE NOT NULL,
    COMENTARIO VARCHAR(255),
    PAGADO BOOLEAN,
    METODO_DE_PAGO VARCHAR(50),
    FOREIGN KEY (ID_TRABAJADOR) REFERENCES TRABAJADORES(ID_TRABAJADOR),
    FOREIGN KEY (ID_SERVICIO) REFERENCES SERVICIOS(ID_SERVICIOS),
    FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES(ID_CLIENTE)
);
