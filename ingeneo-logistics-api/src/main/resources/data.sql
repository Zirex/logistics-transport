CREATE SEQUENCE document_type_document_type_id_seq_1;

CREATE TABLE document_type (
                document_type_id INTEGER NOT NULL DEFAULT nextval('document_type_document_type_id_seq_1'),
                code VARCHAR(3) NOT NULL,
                description VARCHAR(255) NOT NULL,
                state SMALLINT NOT NULL,
                CONSTRAINT document_type_pk PRIMARY KEY (document_type_id)
);


ALTER SEQUENCE document_type_document_type_id_seq_1 OWNED BY document_type.document_type_id;

CREATE SEQUENCE logistict_type_tipo_logistica_id_seq;

CREATE TABLE logistict_type (
                logistict_type_id INTEGER NOT NULL DEFAULT nextval('logistict_type_tipo_logistica_id_seq'),
                description VARCHAR(255) NOT NULL,
                state SMALLINT DEFAULT 1 NOT NULL,
                CONSTRAINT logistict_type_pk PRIMARY KEY (logistict_type_id)
);


ALTER SEQUENCE logistict_type_tipo_logistica_id_seq OWNED BY logistict_type.logistict_type_id;

CREATE SEQUENCE discount_discount_id_seq_1;

CREATE TABLE discount (
                discount_id INTEGER NOT NULL DEFAULT nextval('discount_discount_id_seq_1'),
                rule VARCHAR(255) NOT NULL,
                value NUMERIC(3,2) NOT NULL,
                state SMALLINT DEFAULT 1 NOT NULL,
                logistict_type_id INTEGER NOT NULL,
                CONSTRAINT discount_pk PRIMARY KEY (discount_id)
);


ALTER SEQUENCE discount_discount_id_seq_1 OWNED BY discount.discount_id;

CREATE SEQUENCE warehouse_almacen_id_seq;

CREATE TABLE warehouse (
                warehouse_id INTEGER NOT NULL DEFAULT nextval('warehouse_almacen_id_seq'),
                name VARCHAR(255) NOT NULL,
                address VARCHAR(255) NOT NULL,
                international BOOLEAN DEFAULT false NOT NULL,
                logistict_type_id INTEGER NOT NULL,
                state SMALLINT DEFAULT 1 NOT NULL,
                CONSTRAINT warehouse_pk PRIMARY KEY (warehouse_id)
);


ALTER SEQUENCE warehouse_almacen_id_seq OWNED BY warehouse.warehouse_id;

CREATE SEQUENCE product_type_tipo_producto_id_seq;

CREATE TABLE product_type (
                product_type_id INTEGER NOT NULL DEFAULT nextval('product_type_tipo_producto_id_seq'),
                description VARCHAR(255) NOT NULL,
                state SMALLINT DEFAULT 1 NOT NULL,
                CONSTRAINT product_type_pk PRIMARY KEY (product_type_id)
);


ALTER SEQUENCE product_type_tipo_producto_id_seq OWNED BY product_type.product_type_id;

CREATE TABLE logistics_product (
                product_type_id INTEGER NOT NULL,
                logistict_type_id INTEGER NOT NULL,
                CONSTRAINT logistics_product_pk PRIMARY KEY (product_type_id, logistict_type_id)
);


CREATE SEQUENCE client_cliente_id_seq;

CREATE TABLE client (
                client_id INTEGER NOT NULL DEFAULT nextval('client_cliente_id_seq'),
                document VARCHAR(11) NOT NULL,
                firstname VARCHAR(60) NOT NULL,
                lastname VARCHAR(70) NOT NULL,
                phone VARCHAR(15) NOT NULL,
                mail VARCHAR(70) NOT NULL,
                document_type_id INTEGER NOT NULL,
                state SMALLINT DEFAULT 1 NOT NULL,
                CONSTRAINT client_pk PRIMARY KEY (client_id)
);


ALTER SEQUENCE client_cliente_id_seq OWNED BY client.client_id;

CREATE SEQUENCE order_orden_id_seq;

CREATE TABLE order_1 (
                order_id INTEGER NOT NULL DEFAULT nextval('order_orden_id_seq'),
                order_number VARCHAR(10) NOT NULL,
                create_at TIMESTAMP NOT NULL,
                delivery_date DATE NOT NULL,
                transport_number VARCHAR(15) NOT NULL,
                total NUMERIC(15,2) NOT NULL,
                client_id INTEGER NOT NULL,
                warehouse_id INTEGER NOT NULL,
                state SMALLINT DEFAULT 1 NOT NULL,
                CONSTRAINT order_1_pk PRIMARY KEY (order_id)
);


ALTER SEQUENCE order_orden_id_seq OWNED BY order_1.order_id;

CREATE SEQUENCE route_route_id_seq;

CREATE TABLE route (
                route_id INTEGER NOT NULL DEFAULT nextval('route_route_id_seq'),
                order_id INTEGER NOT NULL,
                warehouse_id INTEGER NOT NULL,
                create_at TIMESTAMP NOT NULL,
                state SMALLINT DEFAULT 1 NOT NULL,
                CONSTRAINT route_pk PRIMARY KEY (route_id)
);


ALTER SEQUENCE route_route_id_seq OWNED BY route.route_id;

CREATE SEQUENCE orden_item_orden_item_id_seq;

CREATE TABLE orden_item (
                orden_item_id INTEGER NOT NULL DEFAULT nextval('orden_item_orden_item_id_seq'),
                order_id INTEGER NOT NULL,
                product_type_id INTEGER NOT NULL,
                discount_id INTEGER NOT NULL,
                quantity INTEGER NOT NULL,
                unit_price NUMERIC(15,2) NOT NULL,
                state SMALLINT NOT NULL,
                CONSTRAINT orden_item_pk PRIMARY KEY (orden_item_id)
);


ALTER SEQUENCE orden_item_orden_item_id_seq OWNED BY orden_item.orden_item_id;

ALTER TABLE client ADD CONSTRAINT document_type_client_fk
FOREIGN KEY (document_type_id)
REFERENCES document_type (document_type_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE warehouse ADD CONSTRAINT tipo_logistica_almacen_fk
FOREIGN KEY (logistict_type_id)
REFERENCES logistict_type (logistict_type_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE logistics_product ADD CONSTRAINT logistict_type_logistics_product_fk
FOREIGN KEY (logistict_type_id)
REFERENCES logistict_type (logistict_type_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE discount ADD CONSTRAINT logistict_type_discount_fk
FOREIGN KEY (logistict_type_id)
REFERENCES logistict_type (logistict_type_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE orden_item ADD CONSTRAINT discount_orden_item_fk
FOREIGN KEY (discount_id)
REFERENCES discount (discount_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE order_1 ADD CONSTRAINT almacen_orden_fk
FOREIGN KEY (warehouse_id)
REFERENCES warehouse (warehouse_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE route ADD CONSTRAINT warehouse_route_fk
FOREIGN KEY (warehouse_id)
REFERENCES warehouse (warehouse_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE orden_item ADD CONSTRAINT product_type_orden_item_fk
FOREIGN KEY (product_type_id)
REFERENCES product_type (product_type_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE logistics_product ADD CONSTRAINT product_type_logistics_product_fk
FOREIGN KEY (product_type_id)
REFERENCES product_type (product_type_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE order_1 ADD CONSTRAINT cliente_orden_fk
FOREIGN KEY (client_id)
REFERENCES client (client_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE orden_item ADD CONSTRAINT orden_orden_item_fk
FOREIGN KEY (order_id)
REFERENCES order_1 (order_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE route ADD CONSTRAINT order_route_fk
FOREIGN KEY (order_id)
REFERENCES order_1 (order_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
