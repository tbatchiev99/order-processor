CREATE TABLE product (
    id IDENTITY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE status (
    id IDENTITY,
    name VARCHAR(50)
);

CREATE SEQUENCE order_nr_seq START WITH 1000;

CREATE TABLE "order" (
    id IDENTITY,
    quantity INT NOT NULL,
    ordered_on TIMESTAMP NOT NULL,
    product_id INT NOT NULL,
    status_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    order_nr BIGINT NOT NULL DEFAULT nextval('order_nr_seq'),
    CONSTRAINT fk_order_product FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT fk_order_status FOREIGN KEY (status_id) REFERENCES status(id)
);

INSERT INTO product(name) VALUES
('Sporting bag NIKE'),
('Leather belt'),
('Flashlight TORCHLIGHT');

INSERT INTO status(name) VALUES
('Successfully processed'),
('Not processed');

 ALTER TABLE "order" ADD CONSTRAINT order_nr_uq UNIQUE(order_nr);

